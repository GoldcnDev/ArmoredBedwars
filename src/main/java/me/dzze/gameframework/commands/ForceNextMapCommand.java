package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceNextMapCommand implements SubCommand {
    private boolean changingMap = false;

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (this.changingMap) {
            MessageUtils.message(sender, "&aMap change already in progress. Please wait...");
            return;
        }
        MessageUtils.message(sender, "&aChanging map. Please wait...");
        this.changingMap = true;
        final World world = Main.getInstance().getMapManager().nextMap();
        GameManager.currentMap = world;
        for (Player player : Bukkit.getOnlinePlayers()) {
            MessageUtils.message(player, "&cThe map has been changed by an admin. Current map: " + world.getName());
        }
        this.changingMap = false;
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
