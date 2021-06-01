package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.listeners.BedListener;
import me.dzze.bedwars.managers.GameManager;
import me.dzze.bedwars.utils.MessageUtils;
import me.dzze.bedwars.utils.PluginMessenger;
import me.dzze.bedwars.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ForceStopCommand extends GameManager implements SubCommand {
    Main main;

    public ForceStopCommand(Main main) {
        super();
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        MessageUtils.message(sender, "&3&lBED&b&lWARS &8| &aForce stopped by an administrator.");
        for (Player players : Bukkit.getOnlinePlayers()) {
            GameManager.gameRunning = false;
            players.setGameMode(GameMode.ADVENTURE);
            main.getServer().getScheduler().cancelTasks(main);
            players.getInventory().clear();
            for (int i = 0; i < 3; i++) {
                players.playSound(players.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6, 1);
            }
            players.sendTitle(MessageUtils.color("&3&lBED&b&lWARS"), MessageUtils.color("&4Game over! &eThanks for playing."), 100, 300, 100);
            BedListener.hasBed.add(Teams.BLUE);
            BedListener.hasBed.add(Teams.RED);
            BedListener.hasBed.add(Teams.WHITE);
            BedListener.hasBed.add(Teams.PURPLE);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            for (Player online : Bukkit.getOnlinePlayers()) {
                PluginMessenger.connect(online, "lobby");
            }
            GameManager.currentMap = this.main.getMapManager().nextMap();
        }, 200L);
    }

    @Override
    public String[] getAliases() {
        return new String[]{"stop", "end"};
    }

    @Override
    public String getPermission() {
        return "bedwars.forcestop";
    }

    @Override
    public String getName() {
        return "forcestop";
    }
}
