package me.dzze.gameframework.listeners;

import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.TimeUnit;

public class CrystalListener implements Listener {
    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        final Entity entity = e.getEntity();
        if (entity instanceof EnderCrystal) {
            final long elapsedMillis = System.currentTimeMillis() - GameManager.getStartTime();
            final Player player = (Player) e.getDamager();
            if (elapsedMillis < TimeUnit.MINUTES.toMillis(3L)) {
                MessageUtils.message(player, "&cYou cannot break upgrade crystals yet! Time remaining: " + TimeUnit.MILLISECONDS.toSeconds(TimeUnit.MINUTES.toMillis(3L) - elapsedMillis) + " seconds");
            }
        }
    }
}
