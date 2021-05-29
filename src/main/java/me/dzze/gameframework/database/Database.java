package me.dzze.gameframework.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private static final String HOST = "168.119.37.243";
    private static final int PORT = 3306;
    private static final String DATABASE = "s3107_bedwars";
    private static final String USERNAME = "u3107_mf7KCEwY2v";
    private static final String PASSWORD = "stEq2Uo1m^V+.n+==0HI9fnI";

    private final HikariConfig config = new HikariConfig();
    private HikariDataSource source;

    public Database() {
        this.config.setJdbcUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE);
        this.config.setDriverClassName(DRIVER_CLASS);
        this.config.setPoolName("bedwars-hikari");
        this.config.setUsername(USERNAME);
        this.config.setPassword(PASSWORD);
    }

    public void connect() {
        if (!this.isConnected()) {
            this.source = new HikariDataSource(this.config);
        }
    }

    public void disconnect() {
        if (this.isConnected()) {
            this.source.close();
        }
    }

    public boolean isConnected() {
        return this.source != null && this.source.isRunning();
    }

    public Connection getConnection() {
        if (this.isConnected()) {
            try {
                return this.source.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
