package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEm2 implements SubCommand {
    Main main;
    public SetEm2(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Em2.world", p.getWorld().getName());
        main.getConfig().set("Em2.x", p.getLocation().getX());
        main.getConfig().set("Em2.y", p.getLocation().getY());
        main.getConfig().set("Em2.z", p.getLocation().getZ());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet emerald gen 2 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"emgen2", "eg2"};
    }

    @Override
    public String getPermission() {
        return "bedwars.em2";
    }

    @Override
    public String getName() {
        return "em2";
    }
}
