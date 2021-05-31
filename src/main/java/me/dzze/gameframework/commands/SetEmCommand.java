package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetEmCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtils.message(sender, "&cThis command may only be executed by players.");
            return;
        }

        final Player player = (Player) sender;

        if (args.length == 1) {
            MessageUtils.message(sender, "&cInvalid arguments! Try: /" + label + " em <1-2>");
            return;
        }

        final String numberStr = args[1];

        try {
            int number = Integer.parseInt(numberStr);
            if (number < 1 || number > 2) {
                MessageUtils.message(sender, "Argument must be 1-2!");
            } else {
                final FileConfiguration configuration = Main.getInstance().getConfig();

                configuration.set("Em." + numberStr, player.getLocation());
                Main.getInstance().saveConfig();

                MessageUtils.message(sender, "&6BEDWARS &8| &aSet emerald gen " + numberStr + " to your location.");
            }
        } catch (NumberFormatException ignored) {
            MessageUtils.message(sender, "Argument must be a number!");
        }
    }

    @Override
    public String[] getAliases() {
        return new String[]{"emgen", "eg"};
    }

    @Override
    public String getPermission() {
        return "bedwars.em";
    }

    @Override
    public String getName() {
        return "emerald";
    }
}