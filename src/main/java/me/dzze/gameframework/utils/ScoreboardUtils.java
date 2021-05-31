package me.dzze.gameframework.utils;

import me.dzze.gameframework.managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {
    public static void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Objective obj = sb.registerNewObjective("First", "dummy", MessageUtils.color("&3&lBED&b&lWARS"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        //  Score score2 = obj.getScore(MessageUtils.color("&a&lPoints &8&l» &e" +
        //        this.data.getPoints(player.getUniqueId())));
        // score2.setScore(2);
        if (TeamManager.getTeam(player) == Teams.BLUE) {
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&9&lBLUE"));
            score3.setScore(3);
        }
        if (TeamManager.getTeam(player) == Teams.RED) {
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&c&lRED"));
            score3.setScore(3);
        }
        if (TeamManager.getTeam(player) == Teams.WHITE) {
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&f&lWHITE"));
            score3.setScore(3);
        }
        if (TeamManager.getTeam(player) == Teams.PURPLE) {
            Score score3 = obj.getScore(MessageUtils.color("&3&lTeam &8&l» &r" +
                    "&5&lPURPLE"));
            score3.setScore(3);
        }
        //Score score3 = obj.getScore(MessageUtils.color("&c&lKills &8&l» &e" +
        //      PlayerKillListener.kills.get(player)));
        //if(PlayerKillListener.kills.get(player) == null || !PlayerKillListener.kills.containsKey(player)){
        //  PlayerKillListener.kills.putIfAbsent(player, 0);
        //}
        // score3.setScore(3);
        player.setScoreboard(sb);
    }
}
