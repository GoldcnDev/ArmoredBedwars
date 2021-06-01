package me.dzze.gameframework;

import me.dzze.gameframework.commands.*;
import me.dzze.gameframework.listeners.*;
import me.dzze.gameframework.managers.GeneratorManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.storage.AbstractSqlDatabase;
import me.dzze.gameframework.storage.SqlDatabase;
import me.dzze.gameframework.ui.ShopUiLoader;
import me.dzze.gameframework.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public AbstractSqlDatabase db;
    public SqlDatabase data;
    BWCommand BWCommand;
    ShopUiLoader shopUI;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.db = new AbstractSqlDatabase(){};
        this.data = new SqlDatabase();
        this.BWCommand = new BWCommand();
        instance = this;
        db.connect();
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new RespawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CompassListener(), this);
        Bukkit.getPluginManager().registerEvents(new TeamManager(), this);
        Bukkit.getPluginManager().registerEvents(new BedListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CrystalListener(this), this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getCommand("connect").setExecutor(new ConnectCommand());
        Bukkit.getPluginCommand("bedwars").setExecutor(BWCommand);
        Bukkit.getPluginCommand("setpoints").setExecutor(new SetPointsCommand(this));
        Bukkit.getPluginCommand("resetpoints").setExecutor(new SetPointsCommand(this));
        BWCommand.registerSubCommand(new ForceStartCommand(this, new GeneratorManager(this)));
        BWCommand.registerSubCommand(new ForceStopCommand(this));
        BWCommand.registerSubCommand(new SetBedBlue(this));
        BWCommand.registerSubCommand(new SetBedPurple(this));
        BWCommand.registerSubCommand(new SetBedRed(this));
        BWCommand.registerSubCommand(new SetBedWhite(this));
        BWCommand.registerSubCommand(new SetCrystalBlue(this));
        BWCommand.registerSubCommand(new SetCrystalRed(this));
        BWCommand.registerSubCommand(new SetCrystalWhite(this));
        BWCommand.registerSubCommand(new SetCrystalPurple(this));
        BWCommand.registerSubCommand(new ShopCommand());
        // Bukkit.getPluginCommand("connect").setExecutor(new ConnectCommand());
        for (Player online : Bukkit.getOnlinePlayers()) {
            ScoreboardUtils.createBoard(online);
        }

        if (db.isConnected()) {
            Bukkit.getLogger().info("Database is connected.");
            data.createTables();
        }
    }

    @Override
    public void onDisable() {
        db.disconnect();
    }


    public static Main getInstance() {
        return instance;
    }
}
