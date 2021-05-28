package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SetBedPurple implements SubCommand {
    Main main;
    public SetBedPurple(Main main){
        this.main = main;
    }
    public static Set<Player> settingBed = new HashSet<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        settingBed.add(p);
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aPlease break the Purple bed."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"setpurplebed", "setpurple"};
    }

    @Override
    public String getPermission() {
        return "bedwars.purple";
    }

    @Override
    public String getName() {
        return "purple";
    }
}
