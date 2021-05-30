package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDia1 implements SubCommand {
    Main main;
    public SetDia1(Main main){
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        Player p = (Player) sender;
        main.getConfig().set("Dia1", p.getLocation());
        main.saveConfig();
        MessageUtils.message(p, "&3&lBED&b&lWARS &8| &aSet diamond generator 1 to your location.");
    }

    @Override
    public String[] getAliases() {
        return new String[]{"diagen1", "dg1"};
    }

    @Override
    public String getPermission() {
        return "bedwars.dia1";
    }

    @Override
    public String getName() {
        return "dia1";
    }
}
