package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetIronThree implements SubCommand {
    Main main;
    public SetIronThree(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Iron3", p.getLocation());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet iron gen 3 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"irongen3", "i3"};
    }

    @Override
    public String getPermission() {
        return "bedwars.iron3";
    }

    @Override
    public String getName() {
        return "iron3";
    }
}
