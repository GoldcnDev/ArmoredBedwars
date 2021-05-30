package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.LocationUtils;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDia4 implements SubCommand {
    Main main;
    public SetDia4(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        LocationUtils.setToConfig("Dia4", p.getLocation());
        main.saveConfig();
        MessageUtils.message(p, "&3&lBED&b&lWARS &8| &aSet diamond generator 4 to your location.");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"diagen4", "dg4"};
    }

    @Override
    public String getPermission() {
        return "bedwars.dia4";
    }

    @Override
    public String getName() {
        return "dia4";
    }
}
