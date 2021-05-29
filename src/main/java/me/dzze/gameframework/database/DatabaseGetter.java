package me.dzze.gameframework.database;

import me.dzze.gameframework.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseGetter {

    private Main plugin;
    public DatabaseGetter(Main plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try{
            ps = plugin.db.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS points "
            + "(NAME VARCHAR(100),UUID VARCHAR(100),POINTS INT(100),PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player){
       try{
           UUID uuid = player.getUniqueId();
           if(!exists(uuid)){
               PreparedStatement ps2 = plugin.db.getConnection().prepareStatement("INSERT IGNORE INTO points " +
                       "(NAME,UUID) VALUES (?,?)");
               ps2.setString(1, player.getName());
               ps2.setString(2, uuid.toString());
               ps2.executeUpdate();
               return;
           }
       } catch (SQLException e){
           e.printStackTrace();
       }
    }

    public boolean exists(UUID uuid){
        try {
            PreparedStatement ps = plugin.db.getConnection().prepareStatement("SELECT * FROM points WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addPoints(UUID uuid, int points){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement ps = plugin.db.getConnection().prepareStatement("UPDATE points SET POINTS=? WHERE UUID=?");
                ps.setInt(1, (getPoints(uuid) + points));
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public void resetPoints(UUID uuid){
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                PreparedStatement ps = plugin.db.getConnection().prepareStatement("UPDATE points SET POINTS=? WHERE UUID=?");
                ps.setInt(1, (0));
                ps.setString(2, uuid.toString());
                ps.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public int getPoints(UUID uuid){
        try {
            PreparedStatement ps = plugin.db.getConnection().prepareStatement("SELECT POINTS FROM points WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()) {
                points = rs.getInt("POINTS");
                return points;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void emptyTable(){
        try{
            PreparedStatement ps = plugin.db.getConnection().prepareStatement("TRUNCATE points");
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeTable(UUID uuid){
        try{
            PreparedStatement ps = plugin.db.getConnection().prepareStatement("DELETE FROM points WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ps.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
