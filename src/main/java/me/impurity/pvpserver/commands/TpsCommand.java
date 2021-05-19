package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TpsCommand extends PlayerUtil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sendTpsMessage(sender);
        return true;
    }
}
