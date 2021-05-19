package me.impurity.pvpserver.util;

import me.impurity.pvpserver.listeners.Data;
import org.bukkit.entity.Player;

public class TimerUtil extends Utils implements Data {
    private long lastMS = getCurrentMS();

    private long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public boolean hasReached(long ms) {
        return (getCurrentMS() - this.lastMS >= ms);
    }

    public boolean sleep(long milliseconds) {
        if (getCurrentMS() - this.lastMS >= milliseconds) {
            reset();
            return true;
        }
        return false;
    }

    public void reset() {
        this.lastMS = getCurrentMS();
    }
}
