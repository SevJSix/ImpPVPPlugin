package me.impurity.pvpserver.runnable;

import me.impurity.pvpserver.Instance;
import me.impurity.pvpserver.util.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class ClearEntities extends BukkitRunnable implements Instance {

    private final List<EntityType> whitelist = Arrays.asList(EntityType.ITEM_FRAME, EntityType.ARMOR_STAND, EntityType.ENDER_CRYSTAL, EntityType.PLAYER);

    @Override
    public void run() {
        int amount = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (Entity entity : chunk.getEntities()) {
                    if (!whitelist.contains(entity.getType())) {
                        amount = chunk.getEntities().length;
                        if (amount > config.getInt("EntityClear.minimum-clear-amount")) {
                            entity.remove();
                        }
                    }
                }
            }
        }
        if (amount > config.getInt("EntityClear.minimum-clear-amount")) {
            MessageUtil.log("&3Cleared &a" + amount + "&3 entities.");
        }
    }
}
