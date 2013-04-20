package com.harryeakins.mathplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.String.format;

public class EarnCommand implements CommandExecutor {
    private static final int MAX_TIMES_TABLE = 10;
    private Random random = new Random();
    private Map<Player, Integer> answers = new HashMap<Player, Integer>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            return false;
        }

        Player player = (Player) commandSender;

        if(command.getName().equals("earn")) {
            if(!answers.containsKey(player)) {
                int num1 = abs(random.nextInt()) % MAX_TIMES_TABLE;
                int num2 = abs(random.nextInt()) % MAX_TIMES_TABLE;
                answers.put(player, num1*num2);

                player.sendMessage(format("%d x %d = ?", num1, num2));
                return true;
            }
        } else if(command.getName().equals("answer")) {
            if(answers.containsKey(player)) {
                if(strings.length == 1) {
                    int givenAnswer = parseInt(strings[0]);
                    if(answers.get(player).equals(givenAnswer)) {
                        MathCoins.changeCoins(player, 20);
                        player.sendMessage("+20 coins!");
                        answers.remove(player);
                        return true;
                    } else {
                        player.sendMessage("Wrong... try again!");
                    }
                }
            }
        } else if(command.getName().equals("coins")) {
            player.sendMessage(String.format("You have %d coins", MathCoins.getCoins(player)));
            return true;
        }
        return false;
    }
}
