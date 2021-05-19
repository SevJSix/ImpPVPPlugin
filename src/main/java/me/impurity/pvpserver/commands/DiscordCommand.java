package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand extends MessageUtil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sendMessage(sender, "&3Join our discord: &a" + getDiscord());
        return true;
    }
}
