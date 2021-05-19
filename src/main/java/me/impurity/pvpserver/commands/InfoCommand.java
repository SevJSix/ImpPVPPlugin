package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.PlayerUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand extends PlayerUtil implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            sendInfoMessage(player);
        } else {
            MessageUtil.sendMessage(sender, notInstanceOfPlayer());
        }
        return true;
    }
}
