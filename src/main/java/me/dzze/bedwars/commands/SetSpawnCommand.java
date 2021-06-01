package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.message(sender, "&cThis command may only be executed by players.");
            return;
        }

        final Player player = (Player) sender;

        if (args.length == 1) {
            MessageUtils.message(sender, "&cInvalid arguments! Try: /" + label + " spawn <1-5>");
            return;
        }

        final String numberStr = args[1];

        try {
            int number = Integer.parseInt(numberStr);
            if (number < 1 || number > 5) {
                MessageUtils.message(sender, "Argument must be 1-5!");
            } else {
                final FileConfiguration configuration = Main.getInstance().getConfig();

                configuration.set(player.getLocation().getWorld().getName() + "." + "Spawn." + numberStr, player.getLocation());
                Main.getInstance().saveConfig();

                MessageUtils.message(sender, "&6BEDWARS &8| &aSet spawn " + numberStr + " to your location.");
            }
        } catch (NumberFormatException ignored) {
            MessageUtils.message(sender, "Argument must be a number!");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"s"};
    }

    @Override
    public String getPermission() {
        return "bedwars.spawn";
    }

    @Override
    public String getName() {
        return "spawn";
    }
}
