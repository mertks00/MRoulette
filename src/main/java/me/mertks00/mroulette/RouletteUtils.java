package me.mertks00.mroulette;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RouletteUtils {

    private static MRoulette plugin;

    public RouletteUtils(MRoulette plugin) {
        RouletteUtils.plugin = plugin;
    }

    public void startTask() {
         new BukkitRunnable() {
            @Override
            public void run() {
                if (plugin.roulettePlayers.keySet().size() >= 1) {
                    cancel();
                    startRoulette();
                }
            }
        }.runTaskTimer(plugin, 0, 20*15);
    }

    public void startRoulette() {
        new BukkitRunnable() {
            int leftTime = 6;

            @Override
            public void run() {
                if (leftTime > 1) {
                    leftTime--;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&bMRoulette&7] &7» &aRuletin çekilmesine son " + String.valueOf(leftTime) + "dk!"));
                    return;
                }
                if(plugin.roulettePlayers.keySet().size() < 1) {
                    Bukkit.broadcastMessage(ChatColor.GREEN +"Oyuncu Bulunamadığından Zamanlayıcı durdu");
                    cancel();
                    startTask();
                    return;
                }
                    Set<Player> playerKey = plugin.roulettePlayers.keySet();
                    List<Player> playerKeyList = new ArrayList<>(playerKey);

                    int size = playerKeyList.size();
                    int randomIndex = new Random().nextInt(size);

                    Player randomPlayer = playerKeyList.get(randomIndex);

                    for (Player players : plugin.roulettePlayers.keySet()) {
                        players.sendTitle(ChatColor.GREEN + randomPlayer.getName(), ChatColor.AQUA + "Rouletten ' 10.000$ Kazandı!");
                    }
                    MRoulette.getEconomy().depositPlayer(randomPlayer, 10000);
                    plugin.roulettePlayers.clear();
                    cancel();
                    startTask();
                }
        }.runTaskTimer(plugin, 0,1200);
    }

}
