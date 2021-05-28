package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.SetBedBlue;
import me.dzze.gameframework.commands.SetBedPurple;
import me.dzze.gameframework.commands.SetBedRed;
import me.dzze.gameframework.commands.SetBedWhite;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;


public class BedListener implements Listener {

    Main main;
    public BedListener(Main main){
        this.main = main;
    }


    Bed red;
    Bed blue;
    Bed purple;
    Bed white;
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(SetBedBlue.settingBed.contains(p)){
            e.setCancelled(true);
            main.getConfig().set("Blue.world", p.getWorld().getName());
            main.getConfig().set("Blue.x", p.getLocation().getX());
            main.getConfig().set("Blue.y", p.getLocation().getY());
            main.getConfig().set("Blue.z", p.getLocation().getZ());
            main.saveConfig();
            SetBedBlue.settingBed.remove(p);
            blue = (Bed) e.getBlock();
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
            white = (Bed) e.getBlock();
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
            red = (Bed) e.getBlock();
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
            purple = (Bed) e.getBlock();
            MessageUtils.message(p, "&3&lB&b&lW &8| &aSet the location of the Purple bed.");
        }

        if(e.getBlock().getType() == Material.LEGACY_BED_BLOCK){
            if(e.getBlock() == purple){
                    Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &5&lPURPLE &cbed has been destroyed!"));
                    e.setDropItems(false);
            }
            if(e.getBlock() == red){
                Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &4&lRED &cbed has been destroyed!"));
                e.setDropItems(false);
            }
            if(e.getBlock() == blue){
                Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &9&lBLUE &cbed has been destroyed!"));
                e.setDropItems(false);
            }
            if(e.getBlock() == white){
                Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lB&B&lW &8| &cThe &f&lWHITE &cbed has been destroyed!"));
                e.setDropItems(false);
            }
        }

    }

    @EventHandler
    public void itemSpawn(ItemSpawnEvent e) {
        if (e.getEntity().getItemStack().getType() ==Material.RED_BED) e.setCancelled(true);
    }




}
