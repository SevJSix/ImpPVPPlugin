package me.impurity.pvpserver.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil extends Utils {
    public static String getPrefix() {
        return config.getString("Plugin.plugin-prefix");
    }

    public static String getDiscord() {
        return config.getString("Plugin.discord-link");
    }

    public static void log(String message) {
        System.out.println(ChatColor.translateAlternateColorCodes('&', getPrefix() + message));
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                sendMessage(player, message);
            }
        }
    }

    public static void sendMessage(Object object, String message) {
        if (object instanceof Player) {
            Player casted = (Player) object;
            casted.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + message));
        } else {
            if (object instanceof CommandSender) {
                CommandSender casted = (CommandSender) object;
                casted.sendMessage(ChatColor.translateAlternateColorCodes('&', getPrefix() + message));
            }
        }
    }
}
