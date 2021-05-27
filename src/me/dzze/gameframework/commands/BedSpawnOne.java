package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedSpawnOne implements SubCommand {
    Main main;
    public BedSpawnOne(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("BedSpawn1.world", p.getWorld().getName());
        main.getConfig().set("BedSpawn1.x", p.getLocation().getX());
        main.getConfig().set("BedSpawn1.y", p.getLocation().getY());
        main.getConfig().set("BedSpawn1.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet bed spawn 1 spawn to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"bedspawnone", "bs1"};
    }

    @Override
    public String getPermission() {
        return "bedwars.bedspawn1";
    }

    @Override
    public String getName() {
        return "bsone";
    }
}
