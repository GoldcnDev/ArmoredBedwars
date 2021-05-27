package me.dzze.gameframework.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.dzze.gameframework.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListener implements PluginMessageListener {

    Main plugin = Main.getInstance();

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if(!channel.equals("BungeeCord")) return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();

        if(subchannel.equals("Connect")) return;

    }

    public void connect(Player player, String server){
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);

        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }
}
