package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends PlayerUtil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sendHelpMessage(sender);
        return true;
    }
}
