package me.dzze.bedwars.commands;

import org.bukkit.command.CommandSender;

public class SetCrystalCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
    }

    @Override
    public String[] getAliases() {
        return new String[] {"setcrystal"};
    }

    @Override
    public String getPermission() {
        return "bedwars.crystal";
    }

    @Override
    public String getName() {
        return "crystal";
    }
}
