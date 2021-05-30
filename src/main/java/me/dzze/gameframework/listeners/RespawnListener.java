package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.ForceStartCommand;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.managers.GeneratorManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnListener extends ForceStartCommand implements Listener {
    private Main plugin;

    public RespawnListener(Main plugin) {
        super(plugin, new GeneratorManager(plugin));
        this.plugin = plugin;
    }


    @EventHandler
    public void onRespawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Player) {
            if (GameManager.gameRunning = true) {
                Player p = (Player) e.getEntity();
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> p.spigot().respawn(), 0L);
                if (!((Player) e.getEntity()).getInventory().contains(Material.BOW)) {
                    ((Player) e.getEntity()).getInventory().addItem(new ItemStack(Material.BOW, 1));
                }
                if (!((Player) e.getEntity()).getInventory().contains(Material.WOODEN_SWORD)) {
                    ((Player) e.getEntity()).getInventory().addItem(new ItemStack(Material.WOODEN_SWORD, 1));
                }
                if (!((Player) e.getEntity()).getInventory().contains(Material.ARROW)) {
                    ((Player) e.getEntity()).getInventory().addItem(new ItemStack(Material.ARROW, 1));
                }
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                    ((Player) e.getEntity()).setHealth(((Player) e.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                    createSpiralAroundPlayer(((Player) e.getEntity()));
                }, 0L, 100L);
            }
        }
    }

    public void createSpiralAroundPlayer(Player player) {
        new BukkitRunnable() {
            double phi = 0;
            public void run() {
                phi += Math.PI / 8;
                double x;
                double y;
                double z;
                Location loc = player.getLocation();
                for (double t = 0; t <= 2 * Math.PI; t += Math.PI / 16) {
                    for (double i = 0; i <= 1; i++) {
                        x = 0.3 * (2 * Math.PI - t) * 0.5 * Math.cos(t + phi + i * Math.PI);
                        y = 0.5 * t;
                        z = 0.3 * (2 * Math.PI - t) * 0.5 * Math.sin(t + phi + i * Math.PI);
                        loc.add(x, y, z);
                        player.spawnParticle(Particle.HEART, loc, 3);
                        loc.subtract(x, y, z);
                    }
                }
                this.cancel();
            }
        }.runTaskTimer(plugin, 0, 3);
    }
}
