package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetIronTwo implements SubCommand {
    Main main;
    public SetIronTwo(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Iron2.world", p.getWorld().getName());
        main.getConfig().set("Iron2.x", p.getLocation().getX());
        main.getConfig().set("Iron2.y", p.getLocation().getY());
        main.getConfig().set("Iron2.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet iron gen 2 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"irongen2", "ig2"};
    }

    @Override
    public String getPermission() {
        return "bedwars.iron2";
    }

    @Override
    public String getName() {
        return "iron2";
    }
}
