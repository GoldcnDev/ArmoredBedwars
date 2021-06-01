package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        ((Player) sender).openInventory(Main.getInstance().shopUI.buildInventory());
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public String getPermission() {
        return "bedwars.shop";
    }

    @Override
    public String getName() {
        return "shop";
    }
}
