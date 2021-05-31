package me.dzze.gameframework.managers;

import org.bukkit.World;

public class GameManager {
    public static boolean gameRunning = false;
    private static long m_startTime;

    public static World currentMap;

    public static void setStartTime(long startTime) {
        m_startTime = startTime;
    }

    public static long getStartTime() {
        return m_startTime;
    }
}
