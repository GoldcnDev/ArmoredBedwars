package me.dzze.bedwars.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public final class MessageUtils {
    private MessageUtils() {
        throw new UnsupportedOperationException();
    }

    public static void message(CommandSender sender, String str) {
        sender.sendMessage(color(str));
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static TextComponent component(String str) {
        return new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', str)));
    }

    public static void broadcast(String s) {
        Bukkit.getServer().broadcastMessage(MessageUtils.color(s));
    }

    public static String toPascalCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
