package me.dzze.gameframework.managers;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class GeneratorManager {

    Main main;
    public GeneratorManager(Main main){
        this.main = main;
    }

    Location iron1 =  new Location(Bukkit.getWorld(main.getConfig().getString("Iron1.world")), main.getConfig().getDouble("Iron1.x"),
            main.getConfig().getDouble("Iron1.y"), main.getConfig().getDouble("Iron1.z"));
    Location iron2 =  new Location(Bukkit.getWorld(main.getConfig().getString("Iron2.world")), main.getConfig().getDouble("Iron2.x"),
            main.getConfig().getDouble("Iron2.y"), main.getConfig().getDouble("Iron2.z"));
    Location iron3 =  new Location(Bukkit.getWorld(main.getConfig().getString("Iron3.world")), main.getConfig().getDouble("Iron3.x"),
            main.getConfig().getDouble("Iron3.y"), main.getConfig().getDouble("Iron3.z"));
    Location iron4 =  new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
            main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z"));

    public void startGens(){
            ArmorStand i1 = (ArmorStand) iron1.getWorld().spawnEntity(iron1, EntityType.ARMOR_STAND);
            i1.setGravity(false);
            i1.setCanPickupItems(false);
            i1.setInvulnerable(true);
            i1.setCustomName(MessageUtils.color("&f&lIRON GEN"));
            i1.setCustomNameVisible(true);
            i1.setVisible(false);
        ArmorStand i2 = (ArmorStand) iron2.getWorld().spawnEntity(iron2, EntityType.ARMOR_STAND);
        i2.setGravity(false);
        i2.setCanPickupItems(false);
        i2.setInvulnerable(true);
        i2.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i2.setCustomNameVisible(true);
        i2.setVisible(false);
        ArmorStand i3 = (ArmorStand) iron3.getWorld().spawnEntity(iron3, EntityType.ARMOR_STAND);
        i3.setGravity(false);
        i3.setCanPickupItems(false);
        i3.setInvulnerable(true);
        i3.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i3.setCustomNameVisible(true);
        i3.setVisible(false);
        ArmorStand i4 = (ArmorStand) iron4.getWorld().spawnEntity(iron4, EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
                i1.getWorld().dropItemNaturally(i1.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i2.getWorld().dropItemNaturally(i2.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i3.getWorld().dropItemNaturally(i3.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i4.getWorld().dropItemNaturally(i4.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
            }, 0L, 40L);
    }

}
