package me.dzze.gameframework.managers;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import net.minecraft.server.v1_16_R2.VillagerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class GeneratorManager {

    Main main;
    public GeneratorManager(Main main){
        this.main = main;
    }

    public void startGens(){
            ArmorStand i1 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity((new Location(Bukkit.getWorld(main.getConfig().getString("Iron1.world")), main.getConfig().getDouble("Iron1.x"),
                    main.getConfig().getDouble("Iron1.y"), main.getConfig().getDouble("Iron1.z"))), EntityType.ARMOR_STAND);
            i1.setGravity(false);
            i1.setCanPickupItems(false);
            i1.setInvulnerable(true);
            i1.setCustomName(MessageUtils.color("&f&lIRON GEN"));
            i1.setCustomNameVisible(true);
            i1.setVisible(false);
        ArmorStand i2 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity( new Location(Bukkit.getWorld(main.getConfig().getString("Iron2.world")), main.getConfig().getDouble("Iron2.x"),
                main.getConfig().getDouble("Iron2.y"), main.getConfig().getDouble("Iron2.z")), EntityType.ARMOR_STAND);
        i2.setGravity(false);
        i2.setCanPickupItems(false);
        i2.setInvulnerable(true);
        i2.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i2.setCustomNameVisible(true);
        i2.setVisible(false);
        ArmorStand i3 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron3.world")), main.getConfig().getDouble("Iron3.x"),
                main.getConfig().getDouble("Iron3.y"), main.getConfig().getDouble("Iron3.z")), EntityType.ARMOR_STAND);
        i3.setGravity(false);
        i3.setCanPickupItems(false);
        i3.setInvulnerable(true);
        i3.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i3.setCustomNameVisible(true);
        i3.setVisible(false);

        ArmorStand i4 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
                main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z")), EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);

        ArmorStand dia1 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
                main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z")), EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);
        ArmorStand dia2 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
                main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z")), EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);
        ArmorStand dia3 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
                main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z")), EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);
        ArmorStand dia4 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Iron4.world")), main.getConfig().getDouble("Iron4.x"),
                main.getConfig().getDouble("Iron4.y"), main.getConfig().getDouble("Iron4.z")), EntityType.ARMOR_STAND);
        i4.setGravity(false);
        i4.setCanPickupItems(false);
        i4.setInvulnerable(true);
        i4.setCustomName(MessageUtils.color("&f&lIRON GEN"));
        i4.setCustomNameVisible(true);
        i4.setVisible(false);

        ArmorStand em1 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Em1.world")), main.getConfig().getDouble("Em1.x"),
                main.getConfig().getDouble("Em1.y"), main.getConfig().getDouble("Em1.z")), EntityType.ARMOR_STAND);
        em1.setGravity(false);
        em1.setCanPickupItems(false);
        em1.setInvulnerable(true);
        em1.setCustomName(MessageUtils.color("&a&lEMERALD GEN"));
        em1.setCustomNameVisible(true);
        em1.setVisible(false);
        ArmorStand em2 = (ArmorStand) Bukkit.getWorld("Boletum").spawnEntity(new Location(Bukkit.getWorld(main.getConfig().getString("Em2.world")), main.getConfig().getDouble("Em2.x"),
                main.getConfig().getDouble("Em2.y"), main.getConfig().getDouble("Em2.z")), EntityType.ARMOR_STAND);
        em2.setGravity(false);
        em2.setCanPickupItems(false);
        em2.setInvulnerable(true);
        em2.setCustomName(MessageUtils.color("&a&lEMERALD GEN"));
        em2.setCustomNameVisible(true);
        em2.setVisible(false);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
                i1.getWorld().dropItemNaturally(i1.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i2.getWorld().dropItemNaturally(i2.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i3.getWorld().dropItemNaturally(i3.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                i4.getWorld().dropItemNaturally(i4.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
            }, 0L, 40L);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lBED&b&lWARS &8| &aThe Emerald generators are now active!"));
                Bukkit.getServer().broadcastMessage(MessageUtils.color("&3&lBED&b&lWARS &8| &aThey will drop each minute. "));
                for(Player online: Bukkit.getOnlinePlayers()){
                    online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                    online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 2);
                    online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3, 3);
                }
            }, 2400L);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(main, () -> {
                em1.getWorld().dropItemNaturally(em1.getLocation(), new ItemStack(Material.EMERALD, 1));
                em2.getWorld().dropItemNaturally(em2.getLocation(), new ItemStack(Material.EMERALD, 1));
            }, 2400L,  1200);
    }

    public void killGens(){
        for(Entity gens : Bukkit.getWorld("Boletum").getEntitiesByClasses(ArmorStand.class)){
            gens.remove();
        }
        for(Entity ores : Bukkit.getWorld("Boletum").getEntities()){
            if(ores instanceof Item){
                ores.remove();
            }
        }
    }

    public void createVillagers(){
        Villager is1 = (Villager) Bukkit.getWorld("Boletum").spawnEntity(null, EntityType.VILLAGER);
        is1.setSilent(true);
        is1.setCustomName("&3&lITEM SHOP");
        is1.setCustomNameVisible(true);
        is1.setVillagerType(Villager.Type.PLAINS);

    }

}
