package me.impurity.pvpserver.listeners;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public interface Data {
    HashMap<UUID, Player> map = new HashMap<>();
}
