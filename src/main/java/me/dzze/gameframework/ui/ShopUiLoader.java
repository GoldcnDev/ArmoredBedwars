package me.dzze.gameframework.ui;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class ShopUiLoader extends AbstractUiLoader {
    public ShopUiLoader(Path path) {
        super(path);
    }

    @Override
    protected Consumer<Player> translateAction(String string) {
        final String[] arguments = string.split(":");
        switch (arguments[0].toLowerCase()) {
            case "message":
                return player -> MessageUtils.message(player, this.rebuildString(arguments));
            case "command":
                return player -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.rebuildString(arguments).replace("%player%", player.getName()));
            case "give":
                final String materialName = arguments[1];
                try {
                    final Material material = Material.valueOf(materialName.toUpperCase());
                    try {
                        final int amount = Integer.parseInt(arguments[2]);
                        return player -> player.getInventory().addItem(new ItemStack(material, amount));
                    } catch (NumberFormatException ignored) {
                    }
                } catch (IllegalArgumentException ignored) {
                }
                break;
            case "take":
                final String materialName1 = arguments[1];
                try {
                    final Material material = Material.valueOf(materialName1.toUpperCase());
                    try {
                        final int amount = Integer.parseInt(arguments[2]);
                        return player -> player.getInventory().remove(new ItemStack(material, amount));
                    } catch (NumberFormatException ignored) {
                    }
                } catch (IllegalArgumentException ignored) {
                }
        }
        return null;
    }

    @Override
    protected void createDefaultShopFile() {
        Main.getInstance().saveResource("shop.yml", false);
    }

    private String rebuildString(String... args) {
        return String.join(":", new ArrayList<>(Arrays.asList(args).subList(1, args.length)));
    }
}
