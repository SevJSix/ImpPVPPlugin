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
    private final long SECONDS = 15 * 1000;
    public EntityDamage() {
        this.timer = new TimerUtil();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            Player player = (Player) event.getEntity();
            if (!map.containsKey(player)) {
                map.put(player, System.currentTimeMillis() + SECONDS);
                MessageUtil.sendMessage(player, "&3You are now in combat.");
            } else if ((map.get(player) - SECONDS) <= System.currentTimeMillis()) {
                map.put(player, System.currentTimeMillis() + SECONDS);
                System.out.println("replace");
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (player == null || map.get(player) == null) return;
                if (map.get(player) <= System.currentTimeMillis()) removePlayer(player);
            }, 20 * 15);
        }
    }

    private void removePlayer(Player player) {
        map.remove(player);
        MessageUtil.sendMessage(player, "&3You are no longer in combat.");
    }
}
