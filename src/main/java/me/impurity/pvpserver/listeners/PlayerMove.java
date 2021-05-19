package me.impurity.pvpserver.listeners;

import me.impurity.pvpserver.util.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove extends Utils implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            if (player.getLocation().getBlock().getType() == Material.PORTAL) {
                event.setCancelled(true);
                player.teleport(randomNetherLocation());
            }
        }
    }
}
