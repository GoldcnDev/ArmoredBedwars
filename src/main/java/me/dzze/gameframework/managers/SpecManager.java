package me.dzze.gameframework.managers;

import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class SpecManager {
    public static Set<Player> spectator = new HashSet<Player>();

    public static void putSpec(Player p) {
        spectator.add(p);
        p.setAllowFlight(true);
        p.setFlying(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, Integer.MAX_VALUE, false));
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.hidePlayer(p);
        }
    }

    public static boolean isSpec(Player p) {
        return (spectator.contains(p) ? true : false);
    }

    public static void giveSpecItem(Player p) {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.color("&aSpectator Compass"));
        item.setItemMeta(meta);
        p.getInventory().setItem(4, item);
    }
}
