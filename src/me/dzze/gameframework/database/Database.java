package me.dzze.gameframework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    String host = "168.119.37.243";
    String port = "3306";
    String database = "s3107_bedwars";
    String username = "u3107_mf7KCEwY2v";
    String password = "stEq2Uo1m^V+.n+==0HI9fnI";

    static Connection connection;

    public boolean isConnected(){
        return (connection == null ? false : true);
    }

    public void connect() throws SQLException,
            ClassNotFoundException {
       if(!isConnected()) {
           connection = DriverManager.getConnection("jdbc:mysql://"
                           + this.host + ":" + this.port + "/" + this.database,
                   this.username, this.password);
       }
    }

    public void disconnect(){
       if(isConnected()){
           try{
               connection.close();
           } catch (SQLException e){
               e.printStackTrace();
           }
       }
    }

    public Connection getConnection(){
        return connection;
    }


}
