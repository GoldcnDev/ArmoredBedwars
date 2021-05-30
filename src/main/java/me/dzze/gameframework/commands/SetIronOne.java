package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetIronOne implements SubCommand {
    Main main;
    public SetIronOne(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Iron1", p.getLocation());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet iron gen 1 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"irongen1", "ig1"};
    }

    @Override
    public String getPermission() {
        return "bedwars.iron1";
    }

    @Override
    public String getName() {
        return "iron1";
    }
}
