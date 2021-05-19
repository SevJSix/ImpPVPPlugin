package me.impurity.pvpserver.commands;

import me.impurity.pvpserver.util.MessageUtil;
import me.impurity.pvpserver.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RenameCommand extends Utils implements CommandExecutor {

    List<Material> shulkerTypes = Arrays.asList(Material.YELLOW_SHULKER_BOX, Material.SILVER_SHULKER_BOX, Material.BLACK_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.PINK_SHULKER_BOX, Material.PURPLE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            if (shulkerTypes.contains(item.getType())) {
                BlockStateMeta blockStateMeta = (BlockStateMeta) item.getItemMeta();
                ShulkerBox shulkerBox = (ShulkerBox) blockStateMeta.getBlockState();
                if (!(shulkerBox.getInventory().getSize() < 27)) {
                    if (args.length > 0) {
                        String name = String.join(" ", args);
                        name = ChatColor.translateAlternateColorCodes('&', name);
                        if (ChatColor.stripColor(name).length() > 35) {
                            name = name.substring(0, 35);
                            MessageUtil.sendMessage(player, "&3The name you entered was over 35 characters long and was shortened.");
                        }
                        if (is32kShulker(shulkerBox.getInventory())) {
                            meta.setDisplayName(name);
                            item.setItemMeta(meta);
                            player.updateInventory();
                            rename32ks(player, item, name);
                        }
                    } else {
                        MessageUtil.sendMessage(player, "&3Name cannot be blank.");
                    }
                } else {
                    MessageUtil.sendMessage(player, "&3Please use a full shulker box.");
                }
            } else {
                MessageUtil.sendMessage(player, "&3You must be holding a shulker box.");
            }
        }
        return true;
    }

    private void rename32ks(Player player, ItemStack item, String name) {
        if (item != null && item.getItemMeta() instanceof BlockStateMeta) {
            BlockStateMeta blockStateMeta = (BlockStateMeta) item.getItemMeta();
            if (blockStateMeta.getBlockState() instanceof ShulkerBox) {
                ShulkerBox shulker = (ShulkerBox) blockStateMeta.getBlockState();
                if (is32kShulker(shulker.getInventory())) {
                    MessageUtil.sendMessage(player, "&3Renamed your 32k's to &r" + name);
                    for (ItemStack shulkerItem : shulker.getInventory().getContents()) {
                        if (shulkerItem == null) continue;
                        ItemMeta itemMeta = shulkerItem.getItemMeta();
                        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
                        shulkerItem.setItemMeta(itemMeta);
                    }
                    blockStateMeta.setBlockState(shulker);
                    item.setItemMeta(blockStateMeta);
                    player.updateInventory();
                } else {
                    MessageUtil.sendMessage(player, "&3The shulker must contain 32ks");
                }
            }
        }
    }

    private boolean is32kShulker(Inventory inventory) {
        if (inventory == null) return false;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                ItemMeta meta = item.getItemMeta();
                if (!meta.hasEnchants()) return false;
                for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
                    if (entry.getValue() == 32767) return true;
                }
            }
        }
        return false;
    }
}
