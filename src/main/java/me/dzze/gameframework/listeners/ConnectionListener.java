package me.dzze.gameframework.listeners;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.commands.ForceStartCommand;
import me.dzze.gameframework.managers.GameManager;
import me.dzze.gameframework.managers.SpecManager;
import me.dzze.gameframework.utils.MessageUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ConnectionListener extends SpecManager implements Listener {
    int onlineCount = Bukkit.getServer().getOnlinePlayers().size();
    Main plugin;
    public ConnectionListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void on(AsyncPlayerPreLoginEvent e) {
       plugin.data.getPoints(e.getUniqueId());
    }

    @EventHandler
    public void on(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String name = p.getDisplayName();
        String newname = name.substring(name.indexOf("|")+1);
        if(!GameManager.gameRunning){
            if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)){
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
            }
            p.getInventory().clear();
            onlineCount++;
            e.setJoinMessage(MessageUtils.color(  newname.trim() + " &dwants to sleep! &6(&f" + onlineCount + "&6/&f12&6)"));
            //p.teleport(Bukkit.getWorld("spawn").getSpawnLocation());
            if(!plugin.data.exists(p.getUniqueId())){
                plugin.data.createPlayer(p);
            }
            for(Player online: Bukkit.getOnlinePlayers()){
                online.removePotionEffect(PotionEffectType.JUMP);
                online.teleport(Bukkit.getWorld("spawn").getSpawnLocation());
                    online.sendMessage(MessageUtils.color("&6&lVote for a map!"));
                    TextComponent archaic = new TextComponent(MessageUtils.component("&71. &aArchaic &7[VOTE]"));
                    archaic.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MessageUtils.component("&aClick to vote for Archaic.")).create()));
                    archaic.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/oitc archaic"));
                    TextComponent trainland = new TextComponent(MessageUtils.component("&72. &aTrainland &7[VOTE]"));
                    trainland.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(MessageUtils.component("&aClick to vote for Trainland.")).create()));
                    trainland.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/oitc trainland"));
                    online.spigot().sendMessage(archaic);
                    online.spigot().sendMessage(trainland);
                plugin.createBoard(online);
            }
            if(onlineCount < 4){
                Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TextComponent.fromLegacyText(MessageUtils.color("&aWaiting for more players..."))));
                }, 0L, 0L);
            }
            else {
                plugin.getServer().getScheduler().cancelTasks(plugin);
                BukkitRunnable countdown = new BukkitRunnable() {
                    int time = 15;
                    @Override
                    public void run() {
                        if (this.time > 0) {
                            for (final Player player : Bukkit.getOnlinePlayers()) {
                                player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                        TextComponent.fromLegacyText(MessageUtils.color("&aStarting &3&lBed&b&lWars &ain &b" + this.time + "&a seconds...")));
                                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 10, 5);
                            }
                            this.time--;
                        }
                        if (this.time == 0) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "oitc start");
                            cancel();
                        }
                    }
                };
                countdown.runTaskTimer(plugin, 0L, 20L);
            }
        } else if(GameManager.gameRunning) {
            if(ForceStartCommand.map == "trainland"){
                p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
            if(ForceStartCommand.map == "archaic"){
                p.teleport(new Location(Bukkit.getWorld(plugin.getConfig().getString("Spawn5.world")), plugin.getConfig().getDouble("Spawn5.x"),
                        plugin.getConfig().getDouble("Spawn.y"), plugin.getConfig().getDouble("Spawn5.z")));
            }
            SpecManager.putSpec(p);
            e.setJoinMessage(MessageUtils.color("&3&lB&b&lW &8| " + p.getDisplayName() + " &7is now spectating."));
            p.setPlayerListName(MessageUtils.color("&7[SPECTATOR] " + p.getName()));
            p.setGameMode(GameMode.ADVENTURE);
            p.getInventory().clear();
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 10000, false, false));
            p.setAllowFlight(true);
            p.setFlying(true);
            giveSpecItem(p);
        }

        }
       
        // TODO: Send any relevant join messages...


    @EventHandler
    public void on(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        String name = p.getDisplayName();
        String newname = name.substring(name.indexOf("|")+1);
        onlineCount--;
        if(onlineCount<4){
            plugin.getServer().getScheduler().cancelTasks(plugin);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TextComponent.fromLegacyText(MessageUtils.color("&aWaiting for more players..."))));
            }, 0L, 0L);
        }
        e.setQuitMessage(MessageUtils.color(  newname.trim() + " &ddoesn't want to sleep anymore. &6(&f" + onlineCount + "&6/&f10&6)"));
    }
}
