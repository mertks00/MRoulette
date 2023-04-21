package me.mertks00.mroulette;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class MRoulette extends JavaPlugin {

    HashMap<Player, Double> roulettePlayers = new HashMap<>();
    private boolean enable = true;
    private static Economy economy;

    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            System.out.println("Çalışamadı");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Eklenti Başlatıldı!");

        getCommand("rulet").setExecutor(new RouletteCommand());

        Bukkit.getPluginManager().registerEvents(new MenuListener(this), this);
        RouletteMenus rouletteMenus = new RouletteMenus(this);
        RouletteUtils rouletteUtils = new RouletteUtils(this);
        rouletteUtils.startTask();

    }

    @Override
    public void onDisable() {
        for(Player players : roulettePlayers.keySet()) {
            economy.depositPlayer(players, 1000);
        }
        roulettePlayers.clear();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public static Economy getEconomy() {
        return economy;
    }

}
