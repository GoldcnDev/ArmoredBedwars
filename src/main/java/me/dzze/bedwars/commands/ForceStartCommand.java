package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.managers.GameManager;
import me.dzze.bedwars.managers.GeneratorManager;
import me.dzze.bedwars.managers.TeamManager;
import me.dzze.bedwars.utils.MessageUtils;
import me.dzze.bedwars.utils.ScoreboardUtils;
import me.dzze.bedwars.utils.Teams;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Objects;


public class ForceStartCommand implements SubCommand {
    Main main;
    GeneratorManager gm;

    public ForceStartCommand(Main main, GeneratorManager gm) {
        this.main = main;
        this.gm = gm;
    }

    public static String map;
    public static HashMap<Player, Integer> team = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        MessageUtils.message(sender, "&3&lBED&b&lWARS &8| &aStarted by an administrator.");
        final World currentMap = GameManager.currentMap;
        currentMap.setAutoSave(false);
        int teamNum = 1;
        for (Player players : Bukkit.getOnlinePlayers()) {
            team.put(players, teamNum);
            if (teamNum == 4) {
                teamNum = 1;
            } else {
                teamNum++;
            }
            /*Random random = new Random();
            int r = random.nextInt(4 - 1 + 1) + 1;
            team.put(players, r);*/
            TeamManager.setTeam(players);
            ScoreboardUtils.createBoard(players);
            GameManager.gameRunning = true;
            GameManager.setStartTime(System.currentTimeMillis());
            if (players.hasPotionEffect(PotionEffectType.INVISIBILITY) || players.hasPotionEffect(PotionEffectType.JUMP)) {
                players.removePotionEffect(PotionEffectType.INVISIBILITY);
                players.removePotionEffect(PotionEffectType.JUMP);
            }
            players.setGameMode(GameMode.SURVIVAL);
            players.sendTitle(MessageUtils.color("&3&lBED&b&lWARS &a&l2.0"), MessageUtils.color("&eAn &4armored&8.pro &ecreation"), 10, 60, 10);
            main.getServer().getScheduler().cancelTasks(main);
            players.getInventory().setItem(0, new ItemStack(Material.WOODEN_SWORD, 1));
            /** if(ArchaicVoteCommand.votes.size() > TrainlandVoteCommand.votes.size()){
             players.teleport(new Location(Bukkit.getWorld(main.getConfig().getString("Spawn5.world")), main.getConfig().getDouble("Spawn5.x"),
             main.getConfig().getDouble("Spawn5.y"), main.getConfig().getDouble("Spawn5.z")));
             map = "archaic";
             }
             if(ArchaicVoteCommand.votes.size() < TrainlandVoteCommand.votes.size()){
             players.teleport(Bukkit.getWorld("world").getSpawnLocation());
             map = "trainland";
             } **/
            /** if(ArchaicVoteCommand.votes.size() == TrainlandVoteCommand.votes.size()){
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
             } **/

            if (TeamManager.getTeam(players) == Teams.BLUE) {
                players.teleport(Objects.requireNonNull(this.main.getConfig().getLocation(currentMap + ".Spawn.1")));
            }

            if (TeamManager.getTeam(players) == Teams.RED) {
                players.teleport(Objects.requireNonNull(this.main.getConfig().getLocation(currentMap + ".Spawn.2")));
            }

            if (TeamManager.getTeam(players) == Teams.WHITE) {
                players.teleport(Objects.requireNonNull(this.main.getConfig().getLocation(currentMap + ".Spawn.3")));
            }

            if (TeamManager.getTeam(players) == Teams.PURPLE) {
                players.teleport(Objects.requireNonNull(this.main.getConfig().getLocation(currentMap + ".Spawn.4")));

            }
            gm.killGens();
            gm.startGens();


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
