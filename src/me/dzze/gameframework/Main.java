package me.dzze.gameframework;

import me.dzze.gameframework.commands.*;
import me.dzze.gameframework.database.Database;
import me.dzze.gameframework.database.DatabaseGetter;
import me.dzze.gameframework.listeners.*;
import me.dzze.gameframework.managers.GeneratorManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import me.dzze.gameframework.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.sql.SQLException;

public class Main extends JavaPlugin {

    private static Main instance;
    public Database db;
    public DatabaseGetter data;
    BWCommand BWCommand;

    @Override
    public void onEnable(){
        this.db = new Database();
        this.data = new DatabaseGetter(this);
        this.BWCommand = new BWCommand();
        createConfig();
        try {
            db.connect();
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getLogger().info("Database not connected.");
        }
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new RespawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CompassListener(), this);
        Bukkit.getPluginManager().registerEvents(new TeamManager(), this);
        Bukkit.getPluginManager().registerEvents(new BedListener(this), this);
        setInstance(this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());
        this.getCommand("connect").setExecutor(new ConnectCommand());
        Bukkit.getPluginCommand("bedwars").setExecutor(BWCommand);
        Bukkit.getPluginCommand("setpoints").setExecutor(new SetPointsCommand(this));
        Bukkit.getPluginCommand("resetpoints").setExecutor(new SetPointsCommand(this));
        BWCommand.registerSubCommand(new SetSpawnFiveCommand(this));
        BWCommand.registerSubCommand(new SetSpawnFourCommand(this));
        BWCommand.registerSubCommand(new SetSpawnThreeCommand(this));
        BWCommand.registerSubCommand(new SetSpawnTwoCommand(this));
        BWCommand.registerSubCommand(new ForceStartCommand(this, new GeneratorManager(this)));
        BWCommand.registerSubCommand(new ForceStopCommand(this));
        BWCommand.registerSubCommand(new SetSpawnOneCommand(this));
        BWCommand.registerSubCommand(new SetIronOne(this));
        BWCommand.registerSubCommand(new SetIronTwo(this));
        BWCommand.registerSubCommand(new SetIronThree(this));
        BWCommand.registerSubCommand(new SetIronFour(this));
        BWCommand.registerSubCommand(new SetEm1(this));
        BWCommand.registerSubCommand(new SetEm2(this));
        BWCommand.registerSubCommand(new SetBedBlue(this));
        BWCommand.registerSubCommand(new SetBedPurple(this));
        BWCommand.registerSubCommand(new SetBedRed(this));
        BWCommand.registerSubCommand(new SetBedWhite(this));
       // Bukkit.getPluginCommand("connect").setExecutor(new ConnectCommand());
        for(Player online: Bukkit.getOnlinePlayers()){
            createBoard(online);
        }

        if(db.isConnected()){
            Bukkit.getLogger().info("Database is connected.");
            data.createTable();
        }
    }

    @Override
    public void onDisable(){
        db.disconnect();
    }


    public void createBoard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Objective obj = sb.registerNewObjective("First", "dummy", MessageUtils.color("&3&lBED&b&lWARS"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score2 = obj.getScore(MessageUtils.color("&a&lPoints &8&l» &e" +
                this.data.getPoints(player.getUniqueId())));
        score2.setScore(2);
        if(TeamManager.getTeam(player) == Teams.BLUE){
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&9&lBLUE"));
            score3.setScore(3);
        }
        if(TeamManager.getTeam(player) == Teams.RED){
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&c&lRED"));
            score3.setScore(3);
        }
        if(TeamManager.getTeam(player) == Teams.WHITE){
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&f&lWHITE"));
            score3.setScore(3);
        }
        if(TeamManager.getTeam(player) == Teams.PURPLE){
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&5&lPURPLE"));
            score3.setScore(3);
        }
        //Score score3 = obj.getScore(MessageUtils.color("&c&lKills &8&l» &e" +
         //      PlayerKillListener.kills.get(player)));
        //if(PlayerKillListener.kills.get(player) == null || !PlayerKillListener.kills.containsKey(player)){
          //  PlayerKillListener.kills.putIfAbsent(player, 0);
        //}
       // score3.setScore(3);
        player.setScoreboard(sb);
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static Main getInstance(){
        return instance;
    }

    private static void setInstance(Main instance){
        Main.instance = instance;
    }



}
