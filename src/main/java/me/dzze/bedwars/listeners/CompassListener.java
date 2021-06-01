package me.dzze.bedwars.listeners;

import me.dzze.bedwars.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CompassListener implements Listener {

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if (e.getAction() != null && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            ItemStack is = e.getItem();
            if (is != null && is.getType().equals(Material.COMPASS)) {
                e.setCancelled(true);
                Player player = e.getPlayer();
                MessageUtils.message(player, "&aThis compass currently does nothing.");
            }
        }
    }
}
