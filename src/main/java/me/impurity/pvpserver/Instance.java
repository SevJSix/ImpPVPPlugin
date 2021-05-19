package me.impurity.pvpserver;

import org.bukkit.configuration.file.FileConfiguration;

public interface Instance {
    PvPServer plugin = PvPServer.getPlugin();
    FileConfiguration config = plugin.getConfig();
}
