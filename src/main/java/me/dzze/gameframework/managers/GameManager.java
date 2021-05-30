package me.dzze.gameframework.managers;

public class GameManager {
    public static boolean gameRunning = false;
    private static long m_startTime;

    public static void setStartTime(long startTime) {
        m_startTime = startTime;
    }

    public static long getStartTime() {
        return m_startTime;
    }
}
