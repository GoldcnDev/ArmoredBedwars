package me.dzze.bedwars.listeners;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.managers.GameManager;
import me.dzze.bedwars.managers.TeamManager;
import me.dzze.bedwars.utils.MessageUtils;
import me.dzze.bedwars.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;

import java.util.HashSet;
import java.util.Set;


public class BedListener implements Listener {

    Main main;
    public BedListener(Main main){
        this.main = main;
    }

    public static Set<Teams> hasBed = new HashSet<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();

        if(e.getBlock().getType() == Material.RED_BED){
            if(e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Purple"))
            || e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Purple2"))){
                if(TeamManager.getTeam(p) == Teams.PURPLE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    if(GameManager.gameRunning){
                        Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &5&lPURPLE &cbed has been destroyed!"));
                        for(Player online: Bukkit.getOnlinePlayers()){
                            if(TeamManager.getTeam(online) == Teams.PURPLE){
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                                online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                            }
                        }
                        hasBed.remove(Teams.PURPLE);
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Red"))
                    || e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Red2"))){
                if(TeamManager.getTeam(p) == Teams.RED){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    if(GameManager.gameRunning){
                        Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &c&lRED &cbed has been destroyed!"));
                        for(Player online: Bukkit.getOnlinePlayers()){
                            if(TeamManager.getTeam(online) == Teams.RED){
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                                online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                            }
                        }
                        hasBed.remove(Teams.RED);
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Blue"))
                    || e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("Blue2"))){

                if(TeamManager.getTeam(p) == Teams.BLUE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    if(GameManager.gameRunning){
                        Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &9&lBLUE &cbed has been destroyed!"));
                        for(Player online: Bukkit.getOnlinePlayers()){
                            if(TeamManager.getTeam(online) == Teams.BLUE){
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                                online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                            }
                        }
                        hasBed.remove(Teams.BLUE);
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("White"))
                    || e.getBlock().getState().getLocation().equals(main.getConfig().getLocation("White2"))){
                if(TeamManager.getTeam(p) == Teams.WHITE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    if(GameManager.gameRunning){
                        Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &f&lWHITE &cbed has been destroyed!"));
                        for(Player online: Bukkit.getOnlinePlayers()){
                            if(TeamManager.getTeam(online) == Teams.WHITE){
                                online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                                online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                            }
                        }
                        hasBed.remove(Teams.WHITE);
                    }
                }
                e.setDropItems(false);
            }


        }

    }

    @EventHandler
    public void itemSpawn(ItemSpawnEvent e) {
        if (e.getEntity().getItemStack().getType() ==Material.RED_BED) e.setCancelled(true);
    }
}
