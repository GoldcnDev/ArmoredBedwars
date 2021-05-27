package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;


public class ForceStartCommand implements SubCommand {
    Main main;
   // RespawnListener respawnListener;
    public ForceStartCommand(Main main){
        this.main = main;
    }
    public static String map;
    public static HashMap<Player, Integer> team = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        MessageUtils.message(sender, "&3&lBED&b&lWARS &8| &aStarted by an administrator.");
        for (Player players : Bukkit.getOnlinePlayers()) {
            Random random = new Random();
            int r = random.nextInt(4 - 1 + 1) + 1;
            team.put(players, r);
            TeamManager.setTeam(players);
            main.createBoard(players);
            GameManager.gameRunning = true;
            if(players.hasPotionEffect(PotionEffectType.INVISIBILITY)){
               players.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
            players.setGameMode(GameMode.ADVENTURE);
            players.sendTitle(MessageUtils.color("&3&lBED&b&lWARS &a&l2.0"), MessageUtils.color("&eAn &4armored&8.pro &ecreation"), 10, 60, 10);
            main.getServer().getScheduler().cancelTasks(main);
           /** if(ArchaicVoteCommand.votes.size() > TrainlandVoteCommand.votes.size()){
                players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn5.world")), main.getConfig().getDouble("Spawn5.x"),
                        main.getConfig().getDouble("Spawn5.y"), main.getConfig().getDouble("Spawn5.z")));
                map = "archaic";
            }
            if(ArchaicVoteCommand.votes.size() < TrainlandVoteCommand.votes.size()){
                players.teleport(Bukkit.getWorld("world").getSpawnLocation());
                map = "trainland";
            }
            if(ArchaicVoteCommand.votes.size() == TrainlandVoteCommand.votes.size()){
                if(spawn.get(players) == spawn.get(players)){
                    spawn.put(players, random.nextInt(4 - 1 + 1) + 1);
                }
                if(spawn.get(players) == 4){
                    players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn4.world")), main.getConfig().getDouble("Spawn4.x"),
                            main.getConfig().getDouble("Spawn4.y"), main.getConfig().getDouble("Spawn4.z")));
                }
                if(spawn.get(players) == 3){
                    players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn3.world")), main.getConfig().getDouble("Spawn3.x"),
                            main.getConfig().getDouble("Spawn3.y"), main.getConfig().getDouble("Spawn3.z")));
                }
                if(spawn.get(players) == 2){
                    players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn3.world")), main.getConfig().getDouble("Spawn3.x"),
                            main.getConfig().getDouble("Spawn3.y"), main.getConfig().getDouble("Spawn3.z")));
                }
                if(spawn.get(players) == 1){
                    players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn4.world")), main.getConfig().getDouble("Spawn4.x"),
                            main.getConfig().getDouble("Spawn4.y"), main.getConfig().getDouble("Spawn4.z")));
                }

                map = "trainland";
            }
            **/
            players.getInventory().clear();
            players.playSound(players.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 7, 1);

            Bukkit.getScheduler().runTaskLater(main, () -> {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "bedwars end");
            }, 6000L);

        }
        // TODO: Start game.
    }

    @Override
    public String[] getAliases() {
        return new String[]{"start", "fs"};
    }

    @Override
    public String getPermission() {
        return "bedwars.forcestart";
    }

    @Override
    public String getName() {
        return "forcestart";
    }
}
