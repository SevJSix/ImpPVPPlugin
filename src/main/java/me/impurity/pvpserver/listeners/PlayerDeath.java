package me.impurity.pvpserver.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getLocation().getY() > 80) event.getDrops().clear();
        if (player.getWorld().getName().equalsIgnoreCase("world_nether")) event.getDrops().clear();
    }
}
