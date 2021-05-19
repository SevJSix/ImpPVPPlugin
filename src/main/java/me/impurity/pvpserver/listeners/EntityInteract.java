package me.impurity.pvpserver.listeners;

import me.impurity.pvpserver.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EntityInteract extends Utils implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) event.getRightClicked();
            if (frame.getItem().getType() != null || frame.getItem().getType() != Material.AIR) {
                openInv(event.getPlayer(), frame.getItem());
                if (!frame.isInvulnerable()) frame.setInvulnerable(true);
                frame.setRotation(Rotation.COUNTER_CLOCKWISE_45);
            }
        }
    }

    private void openInv(Player player, ItemStack item) {
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&c&lImpurityPVP >>"));
        for (int i = 0; i < gui.getContents().length; i++) {
            gui.setItem(i, item);
            gui.getItem(i).setAmount(item.getMaxStackSize());
        }
        player.openInventory(gui);
    }
}
