package me.impurity.pvpserver.listeners;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreproccess extends Utils implements Listener, Data {
    private void dispatchCommand(PlayerCommandPreprocessEvent event, Player player, String command) {
        player.performCommand("pvpserver:" + command);
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().toLowerCase().split(" ");
        switch (args[0].replace("/", "")) {
            case "kill":
                if (config.getBoolean("Plugin.use-kill-command") || !player.isOp() && !player.hasPermission("minecraft.command.kill")) {
                    if (map.containsKey(player)) {
                        MessageUtil.sendMessage(player, "&3You cannot use /kill while in combat.");
                        return;
                    }
                    player.setHealth(0);
                    event.setCancelled(true);
                }
                break;
            case "tps":
                dispatchCommand(event, player, "tps");
                break;
            case "help":
                dispatchCommand(event, player, "help");
                break;
            case "discord":
                dispatchCommand(event, player, "discord");
                break;
            case "info":
                dispatchCommand(event, player, "info");
                break;
            case "ping":
                dispatchCommand(event, player, "ping");
                break;
        }
    }
}
