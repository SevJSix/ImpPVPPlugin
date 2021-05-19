package me.impurity.pvpserver;

import me.impurity.pvpserver.runnable.ClearEntities;
import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.PluginUtil;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvPServer extends JavaPlugin implements CommandExecutor {

    ClearEntities clearEntities = new ClearEntities();

    public static PvPServer getPlugin() {
        return getPlugin(PvPServer.class);
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        PluginUtil.registerListeners();
        PluginUtil.registerCommands();
        PluginUtil.startRunnables();
        MessageUtil.log("&aPvP Server Plugin Enabled!");
    }
}
