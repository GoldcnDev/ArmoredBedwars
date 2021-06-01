package me.dzze.bedwars.utils;

import me.dzze.bedwars.Main;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PluginMessenger {
    public static void connect(Player player, String server) {
        try (final ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream()) {
            try (final DataOutputStream outputStream = new DataOutputStream(byteOutputStream)) {
                outputStream.writeUTF("Connect");
                outputStream.writeUTF(server);
            }
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", byteOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
