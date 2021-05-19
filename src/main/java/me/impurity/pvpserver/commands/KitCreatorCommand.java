package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.PlayerUtil;
import me.impurity.pvpserver.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCreatorCommand extends Utils implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            MessageUtil.sendMessage(player, "&3Warping to KitCreator...");
            player.teleport(kitCreatorLocation());
        } else {
            MessageUtil.sendMessage(sender, PlayerUtil.notInstanceOfPlayer());
        }
        return true;
    }
}
