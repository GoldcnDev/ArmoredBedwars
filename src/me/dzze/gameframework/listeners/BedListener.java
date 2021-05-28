package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.SetBedBlue;
import me.dzze.gameframework.commands.SetBedPurple;
import me.dzze.gameframework.commands.SetBedRed;
import me.dzze.gameframework.commands.SetBedWhite;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BedListener implements Listener {

    Main main;
    public BedListener(Main main){
        this.main = main;
    }

    @EventHandler
    public void onEnable(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(SetBedBlue.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Blue.world", p.getWorld().getName());
            main.getConfig().set("Blue.x", p.getLocation().getX());
            main.getConfig().set("Blue.y", p.getLocation().getY());
            main.getConfig().set("Blue.z", p.getLocation().getZ());
            main.saveConfig();
            SetBedBlue.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the Blue bed."));
        }
        if(SetBedWhite.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("White.world", p.getWorld().getName());
            main.getConfig().set("White.x", p.getLocation().getX());
            main.getConfig().set("White.y", p.getLocation().getY());
            main.getConfig().set("White.z", p.getLocation().getZ());
            main.saveConfig();
            SetBedWhite.settingBed.remove(p);
            p.sendMessage(MessageUtils.color("&3&lB&b&lW &8| &aSet the location of the White bed."));
        }
        if(SetBedRed.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Red.world", p.getWorld().getName());
            main.getConfig().set("Red.x", p.getLocation().getX());
            main.getConfig().set("Red.y", p.getLocation().getY());
            main.getConfig().set("Red.z", p.getLocation().getZ());
            main.saveConfig();
            SetBedRed.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Red bed.");
        }
        if(SetBedPurple.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Purple.world", p.getWorld().getName());
            main.getConfig().set("Purple.x", p.getLocation().getX());
            main.getConfig().set("Purple.y", p.getLocation().getY());
            main.getConfig().set("Purple.z", p.getLocation().getZ());
            main.saveConfig();
            SetBedPurple.settingBed.remove(p);
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Purple bed.");
        }

        if(e.getBlock().getLocation() == new Location(Bukkit.getWorld(main.getConfig().getString("Purple.world")), main.getConfig().getDouble("Purple.x"),
                main.getConfig().getDouble("Purple.y"), main.getConfig().getDouble("Purple.z"))){
            Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &5&lPURPLE &cbed has been destroyed!"));
            e.setDropItems(false);
        }
        if(e.getBlock().getLocation() == new Location(Bukkit.getWorld(main.getConfig().getString("Red.world")), main.getConfig().getDouble("Red.x"),
                main.getConfig().getDouble("Red.y"), main.getConfig().getDouble("Red.z"))){
            Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &c&lRED &cbed has been destroyed!"));
            e.setDropItems(false);
        }
        if(e.getBlock().getLocation() == new Location(Bukkit.getWorld(main.getConfig().getString("White.world")), main.getConfig().getDouble("White.x"),
                main.getConfig().getDouble("White.y"), main.getConfig().getDouble("White.z"))){
            Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &f&lWHITE &cbed has been destroyed!"));
            e.setDropItems(false);
        }
        if(e.getBlock().getLocation() == new Location(Bukkit.getWorld(main.getConfig().getString("Blue.world")), main.getConfig().getDouble("Blue.x"),
                main.getConfig().getDouble("Blue.y"), main.getConfig().getDouble("Blue.z"))){
            Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &9&lBLUE &cbed has been destroyed!"));
            e.setDropItems(false);
        }

    }




}
