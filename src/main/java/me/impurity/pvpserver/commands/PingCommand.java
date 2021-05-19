package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                MessageUtil.sendMessage(sender, "&3Your ping is &a" + PlayerUtil.getPlayerPing(player));
            } else {
                Player other = Bukkit.getPlayer(args[0]);
                if (Bukkit.getOnlinePlayers().contains(other)) {
                    if (other == null) return true;
                    MessageUtil.sendMessage(sender, "&3" + other.getName().concat("'s ") + "ping is &a" + PlayerUtil.getPlayerPing(other));
                }
            }
            return true;
        } else {
            MessageUtil.sendMessage(sender, PlayerUtil.notInstanceOfPlayer());
        }
        return true;
    }
}
