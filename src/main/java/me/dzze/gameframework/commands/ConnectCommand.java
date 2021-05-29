package me.dzze.gameframework.commands;

import me.dzze.gameframework.listeners.BungeeListener;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ConnectCommand implements CommandExecutor {

    private BungeeListener bungeeListener = new BungeeListener();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(cmd.getLabel().equalsIgnoreCase("connect")){
                if(args.length == 1){
                    bungeeListener.connect(player, args[0]);
                } else {
                    MessageUtils.message(player, "&c/connect <server>");
                }
            }
        }
        return true;
    }
}
