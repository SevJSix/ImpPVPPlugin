package me.impurity.pvpserver.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalEvent implements Listener {
    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }
}
