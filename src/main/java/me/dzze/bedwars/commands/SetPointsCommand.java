package me.dzze.bedwars.commands;

import me.dzze.bedwars.Main;
import me.dzze.bedwars.utils.MessageUtils;
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
                        this.plugin.getDatabase().addPoints(p.getUniqueId(), points).whenComplete((v, err) ->
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
                            this.plugin.getDatabase().addPoints(target.getUniqueId(), points).whenComplete((v, err) ->
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
                    this.plugin.getDatabase().resetPoints(p.getUniqueId()).whenComplete((v, err) -> {
                        if (err == null) {
                            MessageUtils.message(p, "&aSet your points to &e0");
                        } else {
                            MessageUtils.message(p, "&cAn error occurred resetting your points!");
                            err.printStackTrace();
                        }
                    });
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    this.plugin.getDatabase().resetPoints(target.getUniqueId()).whenComplete((v, err) -> {
                        if (err == null) {
                            MessageUtils.message(p, "&aSet &e" + target.getName() + "&a's points to &e0");
                        } else {
                            MessageUtils.message(p, "&cAn error occurred resetting &e" + target.getName() + "&c's points!");
                            err.printStackTrace();
                        }
                    });
                }

            }
        }
        return true;
    }
}
