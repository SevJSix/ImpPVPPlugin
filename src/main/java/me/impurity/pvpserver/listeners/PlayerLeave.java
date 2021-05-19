package me.impurity.pvpserver.listeners;

import me.impurity.pvpserver.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener, Data {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (map.containsKey(player)) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', MessageUtil.getPrefix() + "&a" + player.getName() + "&3 just combat logged."));
            player.getInventory().clear();
        }
    }
}
