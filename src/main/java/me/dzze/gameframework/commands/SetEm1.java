package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEm1 implements SubCommand {
    Main main;
    public SetEm1(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Em1", p.getLocation());
        main.saveConfig();
        p.sendMessage(MessageUtils.color("&6BEDWARS &8| &aSet emerald gen 1 to your location."));
    }

    @Override
    public String[] getAliases() {
        return new String[]{"emgen1", "eg1"};
    }

    @Override
    public String getPermission() {
        return "bedwars.em1";
    }

    @Override
    public String getName() {
        return "em1";
    }
}
