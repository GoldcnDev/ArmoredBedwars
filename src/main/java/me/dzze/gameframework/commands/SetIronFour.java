package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetIronFour implements SubCommand {
    Main main;
    public SetIronFour(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Iron4", p.getLocation());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet iron gen 4 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"irongen4", "ig4"};
    }

    @Override
    public String getPermission() {
        return "bedwars.iron4";
    }

    @Override
    public String getName() {
        return "iron4";
    }
}
