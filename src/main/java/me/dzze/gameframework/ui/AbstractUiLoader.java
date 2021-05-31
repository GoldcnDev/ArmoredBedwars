package me.dzze.gameframework.ui;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;

public abstract class AbstractUiLoader implements Listener {
    private final Map<Integer, Consumer<Player>> actions = new HashMap<>();
    private final Map<Integer, ItemStack> items = new HashMap<>();

    private final Path path;

    private FileConfiguration configuration;
    private String title;
    private int size;

    public AbstractUiLoader(Path path) {
        this.path = path;
    }

    public void load() {
        if (Files.notExists(this.path)) {
            try {
                Files.createDirectories(this.path.getParent());
                this.createDefaultShopFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (final BufferedReader reader = Files.newBufferedReader(this.path)) {
            this.configuration = YamlConfiguration.loadConfiguration(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.title = this.configuration.getString("title");
        this.size = this.configuration.getInt("rows") * 9;

        final ConfigurationSection slots = this.configuration.getConfigurationSection("items");
        if (slots != null) {
            for (String slot : slots.getKeys(true)) {
                try {
                    final int slotNum = Integer.parseInt(slot);
                    final String materialName = slots.getString(slot + ".material");
                    try {
                        final Material material = Material.valueOf(materialName.toUpperCase());
                        final int amount = slots.getInt(slot + ".amount");
                        this.items.putIfAbsent(slotNum, new ItemStack(material, amount));
                        for (String action : slots.getStringList("actions")) {
                            this.actions.putIfAbsent(slotNum, this.translateAction(action));
                        }
                    } catch (IllegalArgumentException ignored) {
                        Main.getInstance().getLogger().log(Level.WARNING, "Invalid material! At slot: " + slot + " (" + materialName + ")");
                    }
                } catch (NumberFormatException ignored) {
                    Main.getInstance().getLogger().log(Level.WARNING, "Could not load GUI item '" + slot + "' slot must be a number.");
                }
            }
        }
    }

    protected abstract Consumer<Player> translateAction(String string);

    protected abstract void createDefaultShopFile();

    @EventHandler
    public void on(InventoryClickEvent e) {
        final Inventory inventory = e.getInventory();
        if (inventory.getHolder() instanceof LocalHolder) {
            final Consumer<Player> consumer = this.actions.get(e.getSlot());
            if (consumer != null) {
                consumer.accept((Player) e.getWhoClicked());
            }
            e.setCancelled(true);
        }
    }

    public Inventory buildInventory() {
        final Inventory inventory = Bukkit.createInventory(new LocalHolder(), this.size, MessageUtils.color(this.title));
        for (Map.Entry<Integer, ItemStack> itemStackEntry : this.items.entrySet()) {
            inventory.setItem(itemStackEntry.getKey(), itemStackEntry.getValue());
        }
        return inventory;
    }

    private static class LocalHolder implements InventoryHolder {
        @Override
        public Inventory getInventory() {
            return null;
        }
    }
}
