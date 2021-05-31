package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.managers.SpecManager;
import me.dzze.gameframework.managers.TeamManager;
import me.dzze.gameframework.utils.MessageUtils;
import me.dzze.gameframework.utils.Teams;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener extends SpecManager implements Listener {

    private Main plugin;
    public ChatListener(Main plugin){
        super();
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String name = p.getDisplayName();
        String newname = name.substring(name.indexOf("|")+1);
        if(SpecManager.isSpec(p)){
            e.setCancelled(true);
            TextComponent specChat = new TextComponent(MessageUtils.component("&7[SPEC] " + newname.trim() + " &8» &r" + e.getMessage()));
            specChat.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(MessageUtils.color("&7&oThis player is a spectator.")).create()));
            for(Player online: Bukkit.getOnlinePlayers()){
                online.spigot().sendMessage(specChat);
            }
        } else {
            if(TeamManager.getTeam(p) == Teams.BLUE){
                e.setCancelled(true);
                TextComponent blueChat = new TextComponent(MessageUtils.component("&9&lBLUE " + newname.trim() + " &8» &r" + e.getMessage()));
                for(Player online: Bukkit.getOnlinePlayers()){
                    online.spigot().sendMessage(blueChat);
                }
            }
            if(TeamManager.getTeam(p) == Teams.RED){
                e.setCancelled(true);
                TextComponent redChat = new TextComponent(MessageUtils.component("&c&lRED " + newname.trim() + " &8» &r" + e.getMessage()));
                for(Player online: Bukkit.getOnlinePlayers()){
                    online.spigot().sendMessage(redChat);
                }
            }
            if(TeamManager.getTeam(p) == Teams.PURPLE){
                e.setCancelled(true);
                TextComponent purpleChat = new TextComponent(MessageUtils.component("&5&lPURPLE " + newname.trim() + " &8» &r" + e.getMessage()));
                for(Player online: Bukkit.getOnlinePlayers()){
                    online.spigot().sendMessage(purpleChat);
                }
            }
            if(TeamManager.getTeam(p) == Teams.WHITE){
                e.setCancelled(true);
                TextComponent whiteChat = new TextComponent(MessageUtils.component("&f&lWHITE " + newname.trim() + " &8» &r" + e.getMessage()));
                for(Player online: Bukkit.getOnlinePlayers()){
                    online.spigot().sendMessage(whiteChat);
                }
            }
        }
    }
}
