package com.harryeakins.mathplugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MathCoins {
    private static final int INITIAL_NUM_COINS = 20;

    private static Map<Player, Integer> coins = new HashMap<Player, Integer>();

    public static int getCoins(Player player) {
        if(!coins.containsKey(player) ) {
            setCoins(player, INITIAL_NUM_COINS);
        }
        return coins.get(player);
    }

    public static int changeCoins(Player player, int delta) {
        int coins = getCoins(player);
        return setCoins(player, coins + delta);
    }

    public static int setCoins(Player player, int amount) {
        coins.put(player, amount);
        return amount;
    }
}
