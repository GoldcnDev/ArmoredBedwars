package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.SetBedBlue;
import me.dzze.gameframework.commands.SetBedPurple;
import me.dzze.gameframework.commands.SetBedRed;
import me.dzze.gameframework.commands.SetBedWhite;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import me.dzze.gameframework.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Bed;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.atomic.AtomicReference;


public class BedListener implements Listener {

    Main main;
    public BedListener(Main main){
        this.main = main;
    }

    AtomicReference<Bed> blue = new AtomicReference<>();
    AtomicReference<Bed> red = new AtomicReference<>();
    AtomicReference<Bed> purple = new AtomicReference<>();
    AtomicReference<Bed> white = new AtomicReference<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();

        if(SetBedBlue.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Blue.world", p.getWorld().getName());
            main.getConfig().set("Blue.x", e.getBlock().getLocation().getX());
            main.getConfig().set("Blue.y", e.getBlock().getLocation().getY());
            main.getConfig().set("Blue.z", e.getBlock().getLocation().getZ());
            main.saveConfig();
            SetBedBlue.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Blue bed."));
        }
        if(SetBedWhite.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("White.world", p.getWorld().getName());
            main.getConfig().set("White.x", e.getBlock().getLocation().getX());
            main.getConfig().set("White.y", e.getBlock().getLocation().getY());
            main.getConfig().set("White.z", e.getBlock().getLocation().getZ());
            main.saveConfig();
            SetBedWhite.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the White bed."));
        }
        if(SetBedRed.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Red.world", p.getWorld().getName());
            main.getConfig().set("Red.x", e.getBlock().getLocation().getX());
            main.getConfig().set("Red.y", e.getBlock().getLocation().getY());
            main.getConfig().set("Red.z", e.getBlock().getLocation().getZ());
            main.saveConfig();
            SetBedRed.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Red bed.");
        }
        if(SetBedPurple.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Purple.world", p.getWorld().getName());
            main.getConfig().set("Purple.x", e.getBlock().getLocation().getX());
            main.getConfig().set("Purple.y", e.getBlock().getLocation().getY());
            main.getConfig().set("Purple.z", e.getBlock().getLocation().getZ());
            main.saveConfig();
            SetBedPurple.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Purple bed.");
        }

        if(e.getBlock().getType() == Material.RED_BED){
            if(e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Purple.world")),
                    main.getConfig().getDouble("Purple.x"), main.getConfig().getDouble("Purple.y"),
                    main.getConfig().getDouble("Purple.z")))
            || e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Purple2.world")),
                    main.getConfig().getDouble("Purple2.x"), main.getConfig().getDouble("Purple2.y"),
                    main.getConfig().getDouble("Purple2.z")))){
                if(TeamManager.getTeam(p) == Teams.PURPLE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &5&lPURPLE &cbed has been destroyed!"));
                    for(Player online: Bukkit.getOnlinePlayers()){
                        if(TeamManager.getTeam(online) == Teams.PURPLE){
                            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                            online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                        }
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Red.world")),
                    main.getConfig().getDouble("Red.x"), main.getConfig().getDouble("Red.y"),
                    main.getConfig().getDouble("Red.z")))
                    || e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Red2.world")),
                    main.getConfig().getDouble("Red2.x"), main.getConfig().getDouble("Red2.y"),
                    main.getConfig().getDouble("Red2.z")))){
                if(TeamManager.getTeam(p) == Teams.RED){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &c&lRED &cbed has been destroyed!"));
                    for(Player online: Bukkit.getOnlinePlayers()){
                        if(TeamManager.getTeam(online) == Teams.RED){
                            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                            online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                        }
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Blue.world")),
                    main.getConfig().getDouble("Blue.x"), main.getConfig().getDouble("Blue.y"),
                    main.getConfig().getDouble("Blue.z")))
                    || e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("Blue2.world")),
                    main.getConfig().getDouble("Blue2.x"), main.getConfig().getDouble("Blue2.y"),
                    main.getConfig().getDouble("Blue2.z")))){

                if(TeamManager.getTeam(p) == Teams.BLUE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &9&lBLUE &cbed has been destroyed!"));
                    for(Player online: Bukkit.getOnlinePlayers()){
                        if(TeamManager.getTeam(online) == Teams.BLUE){
                            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                            online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                        }
                    }
                }
                e.setDropItems(false);
            }
            if(e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("White.world")),
                    main.getConfig().getDouble("White.x"), main.getConfig().getDouble("White.y"),
                    main.getConfig().getDouble("White.z")))
                    || e.getBlock().getState().getLocation().equals(new Location(Bukkit.getWorld(main.getConfig().getString("White2.world")),
                    main.getConfig().getDouble("White2.x"), main.getConfig().getDouble("White2.y"),
                    main.getConfig().getDouble("White2.z")))){
                if(TeamManager.getTeam(p) == Teams.WHITE){
                    e.setCancelled(true);
                    MessageUtils.message(p, "&3&lB&b&lW &8| &cYou may not break your own bed.");
                } else {
                    Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &f&lWHITE &cbed has been destroyed!"));
                    for(Player online: Bukkit.getOnlinePlayers()){
                        if(TeamManager.getTeam(online) == Teams.WHITE){
                            online.playSound(online.getLocation(), Sound.ENTITY_WITHER_DEATH, 7, 1);
                            online.sendTitle(MessageUtils.color("&4&lBED BROKEN!"), MessageUtils.color("&6You will not respawn."), 10, 60, 10);
                        }
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
                main.getConfig().set("White2.world", p.getWorld().getName());
                main.getConfig().set("White2.x", e.getClickedBlock().getLocation().getX());
                main.getConfig().set("White2.y", e.getClickedBlock().getLocation().getY());
                main.getConfig().set("White2.z", e.getClickedBlock().getLocation().getZ());
                main.saveConfig();
                SetBedWhite.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the White bed bottom."));
            }
            if(SetBedRed.settingBed2.contains(p)){
                main.getConfig().set("Red2.world", p.getWorld().getName());
                main.getConfig().set("Red2.x", e.getClickedBlock().getLocation().getX());
                main.getConfig().set("Red2.y", e.getClickedBlock().getLocation().getY());
                main.getConfig().set("Red2.z", e.getClickedBlock().getLocation().getZ());
                main.saveConfig();
                SetBedRed.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Red bed bottom."));
            }
            if(SetBedPurple.settingBed2.contains(p)){
                main.getConfig().set("Purple2.world", p.getWorld().getName());
                main.getConfig().set("Purple2.x", e.getClickedBlock().getLocation().getX());
                main.getConfig().set("Purple2.y", e.getClickedBlock().getLocation().getY());
                main.getConfig().set("Purple2.z", e.getClickedBlock().getLocation().getZ());
                main.saveConfig();
                SetBedPurple.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Purple bed bottom."));
            }
            if(SetBedBlue.settingBed2.contains(p)){
                main.getConfig().set("Blue2.world", p.getWorld().getName());
                main.getConfig().set("Blue2.x", e.getClickedBlock().getLocation().getX());
                main.getConfig().set("Blue2.y", e.getClickedBlock().getLocation().getY());
                main.getConfig().set("Blue2.z", e.getClickedBlock().getLocation().getZ());
                main.saveConfig();
                SetBedBlue.settingBed2.remove(p);
                p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Blue bed bottom."));
            }
        }
    }







}
