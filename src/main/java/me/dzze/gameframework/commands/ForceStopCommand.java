package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ForceStopCommand extends GameManager implements SubCommand {
    Main main;
    public ForceStopCommand(Main main){
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
            players.playSound(players.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6, 1);
            players.playSound(players.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6, 1);
            players.playSound(players.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 6, 1);
            players.sendTitle(MessageUtils.color("&3&lBED&b&lWARS"), MessageUtils.color("&4Game over! &eThanks for playing."), 100, 300, 100);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
            for(Player online: Bukkit.getOnlinePlayers()){
                Bukkit.getServer().dispatchCommand(online, "connect lobby");
            }
            Bukkit.getServer().shutdown();
        }, 200L);
        // TODO: Start game.
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
