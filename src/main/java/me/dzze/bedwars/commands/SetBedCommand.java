package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.utils.MessageUtils;
import me.dzze.bedwars.utils.Teams;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Bed;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SetBedCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.message(sender, "&cThis command may only be executed by players.");
            return;
        }

        final Player player = (Player) sender;

        final Block target = player.getTargetBlock(null, 10);
        if (!target.getType().name().endsWith("BED")) {
            MessageUtils.message(player, "&cYou must be looking at a bed to use this command!");
            return;
        }

        if (args.length == 1) {
            MessageUtils.message(sender, "&cInvalid arguments! Try: /" + label + " bed <color>");
            return;
        }

        final String color = args[1];

        try {
            final String teamColor = MessageUtils.toPascalCase(Teams.valueOf(color.toUpperCase()).name());
            final Bed bed = (Bed) target.getBlockData();
            final Bed.Part part = bed.getPart();
            final Bed.Part opposite = part == Bed.Part.HEAD ? Bed.Part.FOOT : Bed.Part.HEAD;

            final FileConfiguration configuration = Main.getInstance().getConfig();

            configuration.set(player.getLocation().getWorld().getName() + "." + teamColor + "." + MessageUtils.toPascalCase(part.name()), target.getLocation());
            configuration.set(player.getLocation().getWorld().getName() + "." + teamColor + "." + MessageUtils.toPascalCase(opposite.name()), this.getSecondBedPartLoc(bed, target.getLocation()));
            Main.getInstance().saveConfig();

            MessageUtils.message(player, "&3&lB&b&lW &8| &aSet the location of the " + teamColor + " bed.");
        } catch (IllegalArgumentException ignored) {
            MessageUtils.message(sender, "&cInvalid team color! Try: " + Arrays.stream(Teams.values()).map(Enum::name).collect(Collectors.joining(", ")));
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"setbed"};
    }

    @Override
    public String getPermission() {
        return "bedwars.bed";
    }

    @Override
    public String getName() {
        return "bed";
    }

    private Location getSecondBedPartLoc(Bed bed, Location location) {
        final BlockFace face = bed.getFacing();
        final Bed.Part part = bed.getPart();
        switch (face) {
            case NORTH:
                if (part == Bed.Part.HEAD) {
                    return location.clone().add(0, 0, 1);
                } else {
                    return location.clone().subtract(0, 0, 1);
                }
            case SOUTH:
                if (part == Bed.Part.HEAD) {
                    return location.clone().subtract(0, 0, 1);
                } else {
                    return location.clone().add(0, 0, 1);
                }
            case EAST:
                if (part == Bed.Part.HEAD) {
                    return location.clone().subtract(1, 0, 0);
                } else {
                    return location.clone().add(1, 0, 0);
                }
            case WEST:
                if (part == Bed.Part.HEAD) {
                    return location.clone().add(1, 0, 0);
                } else {
                    return location.clone().subtract(1, 0, 0);
                }
        }
        return null;
    }
}
