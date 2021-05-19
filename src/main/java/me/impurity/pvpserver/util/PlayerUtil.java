package me.impurity.pvpserver.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PlayerUtil extends Utils {
    public static String notEnabled() {
        return ChatColor.translateAlternateColorCodes('&', "&3This command is not enabled in the config.yml");
    }

    public static void sendHelpMessage(CommandSender sender) {
        if (config.getBoolean("HelpCommand.Enabled")) {
            List<String> help = config.getStringList("HelpCommand.help-message");
            String join = String.join("\n", help);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', join));
        } else {
            MessageUtil.sendMessage(sender, notEnabled());
        }
    }

    public static void sendInfoMessage(CommandSender sender) {
        if (config.getBoolean("InfoCommand.Enabled")) {
            Player player = (Player) sender;
            String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
            int onlinePlayers = Bukkit.getOnlinePlayers().size();
            long seed = Objects.requireNonNull(Bukkit.getWorld("world")).getSeed();
            List<String> info = config.getStringList("InfoCommand.info-message");
            String join = String.join("\n", info)
                    .replace("{discord}", MessageUtil.getDiscord())
                    .replace("{date}", date)
                    .replace("{online}", String.valueOf(onlinePlayers))
                    .replace("{seed}", String.valueOf(seed))
                    .replace("{ping}", String.valueOf(getPlayerPing(player)));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', join));
        } else {
            MessageUtil.sendMessage(sender, notEnabled());
        }
    }

    public static void sendTpsMessage(CommandSender sender) {
        if (config.getBoolean("TpsCommand.Enabled")) {
            List<String> tpsmsg = config.getStringList("TpsCommand.tps-message");
            String join = String.join("\n", tpsmsg).
                    replace("{tps}", getTPSAsString()).
                    replace("{detailed_tps}", String.valueOf(getRawTps()));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', join));
        } else {
            MessageUtil.sendMessage(sender, notEnabled());
        }
    }

    public static String notInstanceOfPlayer() {
        return ChatColor.DARK_AQUA + "You must be in-game to execute this command.";
    }

    public static int getPlayerPing(Player player) {
        try {
            String a = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> b = Class.forName("org.bukkit.craftbukkit." + a + ".entity.CraftPlayer");
            Object c = b.getMethod("getHandle", new Class[0]).invoke(player);
            int d = (Integer) c.getClass().getDeclaredField("ping").get(c);
            if (!plugin.getConfig().getBoolean("disableNegative"))
                d = Math.max(d, 0);
            return d;
        } catch (Exception e) {
            return config.getBoolean("disableNegative") ? 0 : -1;
        }
    }
}
