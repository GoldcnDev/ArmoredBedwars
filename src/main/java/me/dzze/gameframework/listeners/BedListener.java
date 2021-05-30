package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.SetBedBlue;
import me.dzze.gameframework.commands.SetBedPurple;
import me.dzze.gameframework.commands.SetBedRed;
import me.dzze.gameframework.commands.SetBedWhite;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import me.dzze.gameframework.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

        if(SetBedBlue.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Blue", e.getBlock().getLocation());
            main.saveConfig();
            SetBedBlue.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Blue bed."));
        }
        if(SetBedWhite.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("White", e.getBlock().getLocation());
            main.saveConfig();
            SetBedWhite.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the White bed."));
        }
        if(SetBedRed.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Red", e.getBlock().getLocation());
            main.saveConfig();
            SetBedRed.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Red bed.");
        }
        if(SetBedPurple.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Purple", e.getBlock().getLocation());
            main.saveConfig();
            SetBedPurple.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Purple bed.");
        }

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

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(SetBedWhite.settingBed2.contains(p)){
                main.getConfig().set("White2", e.getClickedBlock().getLocation());
                main.saveConfig();
                SetBedWhite.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the White bed bottom."));
            }
            if(SetBedRed.settingBed2.contains(p)){
                main.getConfig().set("Red2", e.getClickedBlock().getLocation());
                main.saveConfig();
                SetBedRed.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Red bed bottom."));
            }
            if(SetBedPurple.settingBed2.contains(p)){
                main.getConfig().set("Purple2", e.getClickedBlock().getLocation());
                main.saveConfig();
                SetBedPurple.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Purple bed bottom."));
            }
            if(SetBedBlue.settingBed2.contains(p)){
                main.getConfig().set("Blue2", e.getClickedBlock().getLocation());
                main.saveConfig();
                SetBedBlue.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Blue bed bottom."));
            }
        }
    }
}
