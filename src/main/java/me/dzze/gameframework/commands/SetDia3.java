package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.LocationUtils;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDia3 implements SubCommand {
    Main main;
    public SetDia3(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        LocationUtils.setToConfig("Dia3", p.getLocation());
        main.saveConfig();
        MessageUtils.message(p, "&3&lBED&b&lWARS &8| &aSet diamond generator 3 to your location.");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"diagen3", "dg3"};
    }

    @Override
    public String getPermission() {
        return "bedwars.dia3";
    }

    @Override
    public String getName() {
        return "dia3";
    }
}
