package me.impurity.pvpserver.util;

import me.impurity.pvpserver.commands.*;
import me.impurity.pvpserver.listeners.*;
import me.impurity.pvpserver.listeners.EntityDamage;
import me.impurity.pvpserver.runnable.ClearEntities;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.List;

public class PluginUtil extends Utils {

    private static final PluginManager pluginManager = Bukkit.getServer().getPluginManager();
    private static final List<Listener> listeners = Arrays.asList( new PlayerMove(), new PlayerRespawn(), new CommandPreproccess(), new PlayerDeath(),
            new PortalEvent(), new FallDamage(), new EntityInteract(), new EntityDamage(), new PlayerLeave());
    private static final BukkitScheduler scheduler = Bukkit.getScheduler();
    private static final ClearEntities clearEntities = new ClearEntities();

    public static void registerListeners() {
        MessageUtil.log("&3Registering Listeners...");
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, plugin);
        }
    }

    public static void registerCommands() {
        MessageUtil.log("&3Registering Commands...");
        plugin.getCommand("help").setExecutor(new HelpCommand());
        plugin.getCommand("tps").setExecutor(new TpsCommand());
        plugin.getCommand("rename").setExecutor(new RenameCommand());
        plugin.getCommand("kitcreator").setExecutor(new KitCreatorCommand());
        plugin.getCommand("info").setExecutor(new InfoCommand());
        plugin.getCommand("ping").setExecutor(new PingCommand());
        plugin.getCommand("discord").setExecutor(new DiscordCommand());
        plugin.getCommand("nether").setExecutor(new NetherCommand());
    }

    public static void startRunnables() {
        MessageUtil.log("&3Starting Runnables...");
        scheduler.scheduleSyncRepeatingTask(plugin, clearEntities, 0L, 20 * 60L);
    }
}
