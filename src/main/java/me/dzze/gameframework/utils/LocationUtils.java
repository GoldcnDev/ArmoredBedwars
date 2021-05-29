package me.dzze.gameframework.utils;

import me.dzze.gameframework.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {

    static Main main;
    public LocationUtils(Main main){
        LocationUtils.main = main;
    }

    public static void setToConfig(String s, Location loc){
        main.getConfig().set(s + ".world", loc.getWorld().getName());
        main.getConfig().set(s + ".x", loc.getX());
        main.getConfig().set(s + ".y", loc.getY());
        main.getConfig().set(s + ".z", loc.getZ());
        main.saveConfig();
    }

    public static Location getFromConfig(String s){
        return new Location(Bukkit.getWorld(main.getConfig().getString(s+".world")),
                main.getConfig().getDouble(s+".x"), main.getConfig().getDouble(s+".y"),
                main.getConfig().getDouble(s+".z"));
    }

}
