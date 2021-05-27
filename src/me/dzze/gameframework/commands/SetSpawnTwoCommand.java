package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnTwoCommand implements SubCommand {
    Main main;
    public SetSpawnTwoCommand(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Spawn2.world", p.getWorld().getName());
        main.getConfig().set("Spawn2.x", p.getLocation().getX());
        main.getConfig().set("Spawn2.y", p.getLocation().getY());
        main.getConfig().set("Spawn2.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet spawn 2 spawn to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"spawntwo", "s2"};
    }

    @Override
    public String getPermission() {
        return "bedwars.spawn2";
    }

    @Override
    public String getName() {
        return "spawn2";
    }
}
