package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnOneCommand implements SubCommand {
    Main main;
    public SetSpawnOneCommand(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Spawn1.world", p.getWorld().getName());
        main.getConfig().set("Spawn1.x", p.getLocation().getX());
        main.getConfig().set("Spawn1.y", p.getLocation().getY());
        main.getConfig().set("Spawn1.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet spawn 1 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"spawnone", "s1"};
    }

    @Override
    public String getPermission() {
        return "bedwars.spawn1";
    }

    @Override
    public String getName() {
        return "spawn1";
    }
}
