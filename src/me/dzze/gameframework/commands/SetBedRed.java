package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SetBedRed implements SubCommand {
    Main main;
    public SetBedRed(Main main){
        this.main = main;
    }
    public static Set<Player> settingBed = new HashSet<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Red.world", p.getWorld().getName());
        main.getConfig().set("Red.x", p.getLocation().getX());
        main.getConfig().set("Red.y", p.getLocation().getY());
        main.getConfig().set("Red.z", p.getLocation().getZ());
        main.saveConfig();
        settingBed.add(p);
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet red bed location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"setredbed", "setred"};
    }

    @Override
    public String getPermission() {
        return "bedwars.red";
    }

    @Override
    public String getName() {
        return "red";
    }
}
