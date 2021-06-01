package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SetCrystalPurple implements SubCommand {
    Main main;
    public SetCrystalPurple(Main main){
        this.main = main;
    }
    public static Set<Player> isSetting = new HashSet<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        isSetting.add(p);
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aBreak the purple crystal please."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"crpurple", "cpurple"};
    }

    @Override
    public String getPermission() {
        return "bedwars.crystalpurple";
    }

    @Override
    public String getName() {
        return "crystalpurple";
    }
}
