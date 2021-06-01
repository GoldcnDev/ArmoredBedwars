package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.managers.GameManager;
import me.dzze.bedwars.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceNextMapCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (Main.getInstance().getMapManager().isChangeInProgress()) {
            MessageUtils.message(sender, "&aMap change already in progress. Please wait...");
            return;
        }
        MessageUtils.message(sender, "&aChanging map. Please wait...");
        final World world = Main.getInstance().getMapManager().nextMap();
        GameManager.currentMap = world;
        for (Player player : Bukkit.getOnlinePlayers()) {
            MessageUtils.message(player, "&cThe map has been changed by an admin. Current map: " + world.getName());
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"rotatemap"};
    }

    @Override
    public String getPermission() {
        return "bedwars.nextmap";
    }

    @Override
    public String getName() {
        return "nextmap";
    }
}
