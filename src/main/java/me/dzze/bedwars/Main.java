package me.dzze.bedwars;

import me.dzze.bedwars.commands.*;
import me.dzze.bedwars.listeners.*;
import me.dzze.bedwars.managers.GameManager;
import me.dzze.bedwars.managers.GeneratorManager;
import me.dzze.bedwars.managers.MapManager;
import me.dzze.bedwars.managers.TeamManager;
import me.dzze.bedwars.storage.SqlDatabase;
import me.dzze.bedwars.ui.ShopUiLoader;
import me.dzze.bedwars.utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private SqlDatabase database;
    public ShopUiLoader shopUi;

    private MapManager mapManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.initStorage();
        this.initCommands();
        this.initListeners();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        instance = this;


        // START SHOP STUFF

        this.shopUi = new ShopUiLoader(this.getDataFolder().toPath().resolve("shop.yml"));
        Bukkit.getPluginManager().registerEvents(this.shopUi, this);
        this.shopUi.load();

        // END SHOP STUFF

        this.mapManager = new MapManager(this);
        GameManager.currentMap = this.mapManager.nextMap();

        for (Player online : Bukkit.getOnlinePlayers()) {
            ScoreboardUtils.createBoard(online);
        }
    }

    @Override
    public void onDisable() {
        this.mapManager.deleteAndUnloadCurrentMap();
        this.database.disconnect();
    }

    private void initStorage() {
        this.database = new SqlDatabase();
        this.database.connect();

        if (this.database.isConnected()) {
            Bukkit.getLogger().info("Database is connected.");
            this.database.createTables();
        }
    }

    private void initCommands() {
        final PluginCommand bedWarsCommand = this.getCommand("bedwars");
        if (bedWarsCommand != null) {
            final BWCommand bwCommand = new BWCommand();
            bedWarsCommand.setExecutor(bwCommand);

            // Sub commands:

            bwCommand.registerSubCommand(new SetDiamondCommand());
            bwCommand.registerSubCommand(new SetSpawnCommand());
            bwCommand.registerSubCommand(new SetIronCommand());
            bwCommand.registerSubCommand(new SetEmCommand());
            bwCommand.registerSubCommand(new ForceNextMapCommand());
            bwCommand.registerSubCommand(new SetBedCommand());

            bwCommand.registerSubCommand(new ForceStartCommand(this, new GeneratorManager(this)));
            bwCommand.registerSubCommand(new ForceStopCommand(this));
            bwCommand.registerSubCommand(new SetCrystalBlue(this));
            bwCommand.registerSubCommand(new SetCrystalRed(this));
            bwCommand.registerSubCommand(new SetCrystalWhite(this));
            bwCommand.registerSubCommand(new SetCrystalPurple(this));

            // SHOP COMMAND
            bwCommand.registerSubCommand(new ShopCommand());
        }

        final PluginCommand connectCommand = this.getCommand("connect");
        if (connectCommand != null) {
            connectCommand.setExecutor(new ConnectCommand());
        }

        final PluginCommand resetPointsCommand = this.getCommand("resetpoints");
        if (resetPointsCommand != null) {
            resetPointsCommand.setExecutor(new SetPointsCommand(this));
        }

        final PluginCommand setPointsCommand = this.getCommand("setpoints");
        if (setPointsCommand != null) {
            setPointsCommand.setExecutor(new SetPointsCommand(this));
        }
    }

    private void initListeners() {
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(this), this);
        pluginManager.registerEvents(new RespawnListener(this), this);
        pluginManager.registerEvents(new ChatListener(this), this);
        pluginManager.registerEvents(new CompassListener(), this);
        pluginManager.registerEvents(new TeamManager(), this);
        pluginManager.registerEvents(new BedListener(this), this);
        pluginManager.registerEvents(new CrystalListener(this), this);
    }

    public MapManager getMapManager() {
        return this.mapManager;
    }

    public SqlDatabase getDatabase() {
        return this.database;
    }
}
