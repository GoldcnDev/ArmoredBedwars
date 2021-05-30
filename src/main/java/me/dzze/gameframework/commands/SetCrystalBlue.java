package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SetCrystalBlue implements SubCommand {
    Main main;
    public SetCrystalBlue(Main main){
        this.main = main;
    }
    public static Set<Player> isSetting = new HashSet<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        isSetting.add(p);
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aPlease break the Blue Crystal."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"crblue", "cblue"};
    }

    @Override
    public String getPermission() {
        return "bedwars.crystalblue";
    }

    @Override
    public String getName() {
        return "crystalblue";
    }
}
