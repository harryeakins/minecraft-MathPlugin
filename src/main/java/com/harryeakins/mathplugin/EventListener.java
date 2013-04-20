package com.harryeakins.mathplugin;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;

import static java.lang.String.format;

public class EventListener implements Listener {
    public static final int BLOCK_BREAK_FEE = 1;
    public static final int ITEM_CRAFT_FEE = 5;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int coins = MathCoins.getCoins(player);
        if(coins >= BLOCK_BREAK_FEE) {
            MathCoins.changeCoins(player, -1 * BLOCK_BREAK_FEE);
        } else {
            event.setCancelled(true);
            player.sendMessage("You need more coins!");
        }
    }

    @EventHandler
    public void onItemCraftEvent(CraftItemEvent event) {
        HumanEntity human = event.getWhoClicked();
        if(!(human instanceof Player)) {
            return;
        }
        Player player = (Player) human;
        int coins = MathCoins.getCoins(player);
        if(coins >= ITEM_CRAFT_FEE) {
            MathCoins.changeCoins(player, -1 * ITEM_CRAFT_FEE);
        } else {
            event.setCancelled(true);
            player.sendMessage(format("You need %d coins to craft! (You currently have %d coins)", ITEM_CRAFT_FEE, coins));
        }
    }
}
