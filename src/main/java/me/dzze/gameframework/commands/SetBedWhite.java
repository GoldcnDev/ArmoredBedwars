package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SetBedWhite implements SubCommand {
    Main main;
    public SetBedWhite(Main main){
        this.main = main;
    }
    public static Set<Player> settingBed = new HashSet<>();
    public static Set<Player> settingBed2 = new HashSet<>();


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("White.world", p.getWorld().getName());
        main.getConfig().set("White.x", p.getLocation().getX());
        main.getConfig().set("White.y", p.getLocation().getY());
        main.getConfig().set("White.z", p.getLocation().getZ());
        main.saveConfig();
        settingBed.add(p);
        settingBed2.add(p);
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aPlease break the White bed."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"setwhitebed", "setwhite"};
    }

    @Override
    public String getPermission() {
        return "bedwars.white";
    }

    @Override
    public String getName() {
        return "white";
    }
}