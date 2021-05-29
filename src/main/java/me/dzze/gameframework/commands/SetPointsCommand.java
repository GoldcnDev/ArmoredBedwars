package me.dzze.gameframework.commands;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPointsCommand implements CommandExecutor {

    private Main plugin;

    public SetPointsCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getLabel().equalsIgnoreCase("setpoints")) {
            if (!p.hasPermission("pvpgames.setpoints")) {
                MessageUtils.message(p, "&4No permission.");
            } else {
                if (args.length == 0) {
                    MessageUtils.message(p, "&4Invalid arguments.");
                }
                if (args.length == 1) {
                    try {
                        final int points = Integer.parseInt(args[0]);
                        plugin.data.addPoints(p.getUniqueId(), points).whenComplete((v, err) ->
                                MessageUtils.message(p, "&aSet your points to &e" + points)
                        );
                    } catch (NumberFormatException ignored) {
                        MessageUtils.message(p, "&cPoints must be a number!");
                    }
                }
                if (args.length == 2) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        MessageUtils.message(p, "&cCould not find the player specified.");
                    } else {
                        try {
                            final int points = Integer.parseInt(args[1]);
                            plugin.data.addPoints(target.getUniqueId(), points).whenComplete((v, err) ->
                                    MessageUtils.message(p, "&aSet &e" + target.getName() + "&a's points to &e" + points)
                            );
                        } catch (NumberFormatException ignored) {
                            MessageUtils.message(p, "&cPoints must be a number!");
                        }
                    }
                }

            }
        }

        if (cmd.getLabel().equalsIgnoreCase("resetpoints")) {
            if (!p.hasPermission("pvpgames.resetpoints")) {
                MessageUtils.message(p, "&4No permission.");
            } else {
                if (args.length == 0) {
                    plugin.data.resetPoints(p.getUniqueId());
                    MessageUtils.message(p, "&aSet your points to &e0");
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    plugin.data.resetPoints(target.getUniqueId());
                    MessageUtils.message(p, "&aSet &e" + target.getName() + "&a's points to &e0");
                }

            }
        }
        return true;
    }
}
