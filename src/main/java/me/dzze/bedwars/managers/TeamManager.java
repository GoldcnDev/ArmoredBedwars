package me.dzze.bedwars.managers;

import me.dzze.bedwars.commands.ForceStartCommand;
import me.dzze.bedwars.utils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class TeamManager implements Listener {
    static HashMap<Player, Teams> team = new HashMap<>();

    public static void setTeam(Player p) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (team.get(online) != Teams.BLUE) {
                if (ForceStartCommand.team.get(p) == 1) {
                    team.put(p, Teams.BLUE);
                }
            }
            if (team.get(online) != Teams.RED) {
                if (ForceStartCommand.team.get(p) == 2) {
                    team.put(p, Teams.RED);
                }
            }
            if (team.get(online) != Teams.WHITE) {
                if (ForceStartCommand.team.get(p) == 3) {
                    team.put(p, Teams.WHITE);
                }
            }
            if (team.get(online) != Teams.PURPLE) {
                if (ForceStartCommand.team.get(p) == 4) {
                    team.put(p, Teams.PURPLE);
                }
            }
        }
    }

    public static Teams getTeam(Player p) {
        return team.get(p);
    }

}
