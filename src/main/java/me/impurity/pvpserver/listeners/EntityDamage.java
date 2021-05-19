package me.impurity.pvpserver.listeners;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.TimerUtil;
import me.impurity.pvpserver.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage extends Utils implements Listener, Data {

    TimerUtil timer;

    public EntityDamage() {
        this.timer = new TimerUtil();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                Player player = (Player) event.getEntity();
                if (!map.containsKey(player.getUniqueId())) {
                    map.put(player.getUniqueId(), player);
                    MessageUtil.sendMessage(player, "&3You are now in combat.");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> removePlayer(player), 20 * 10L);
                }
            }
        }
    }

    private void removePlayer(Player player) {
        map.remove(player.getUniqueId());
        MessageUtil.sendMessage(player, "&3You are no longer in combat.");
    }
}
