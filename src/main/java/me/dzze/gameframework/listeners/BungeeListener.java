package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeListener {
    public void connect(Player player, String server) {
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
