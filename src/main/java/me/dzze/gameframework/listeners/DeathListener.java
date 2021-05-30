package me.dzze.gameframework.listeners;

import me.dzze.gameframework.managers.SpecManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        String name = p.getDisplayName();
        String newname = name.substring(name.indexOf("|") + 1);
        if (BedListener.hasBed.contains(TeamManager.getTeam(p))) {
            SpecManager.putSpec(p);
            p.playSound(p.getLocation(), Sound.ENTITY_GHAST_DEATH, 10, 1);
            p.sendTitle(MessageUtils.color("&4&lYou were eliminated!"), "", 10, 60, 10);
            p.getInventory().clear();
            SpecManager.giveSpecItem(p);
            MessageUtils.broadcast(newname + " &cwas eliminated!");
            switch (TeamManager.getTeam(p)) {
                case PURPLE:
                    MessageUtils.broadcast("&bTeam &5&lPURPLE &cis out of the game!");
                    break;
                case RED:
                    MessageUtils.broadcast("&bTeam &c&lRED &cis out of the game!");
                    break;
                case BLUE:
                    MessageUtils.broadcast("&bTeam &9&lBLUE &cis out of the game!");
                    break;
                case WHITE:
                    MessageUtils.broadcast("&bTeam &f&lWHITE &cis out of the game!");
                    break;
            }
        }
    }
}
