package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnThreeCommand implements SubCommand {
    Main main;
    public SetSpawnThreeCommand(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Spawn3.world", p.getWorld().getName());
        main.getConfig().set("Spawn3.x", p.getLocation().getX());
        main.getConfig().set("Spawn3.y", p.getLocation().getY());
        main.getConfig().set("Spawn3.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet level 3 spawn to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"spawnthree", "s3"};
    }

    @Override
    public String getPermission() {
        return "bedwars.spawn3";
    }

    @Override
    public String getName() {
        return "spawn3";
    }
}
