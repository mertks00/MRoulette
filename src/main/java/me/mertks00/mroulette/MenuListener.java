package me.mertks00.mroulette;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class MenuListener implements Listener {

    private MRoulette plugin;

    public MenuListener(MRoulette plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Rulet Menü")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case DIAMOND:
                    RouletteMenus.boughtMenu(player);
                    break;
                case PAPER:
                    if(plugin.roulettePlayers == null) {
                        player.sendTitle(ChatColor.GREEN + "MRoulette", ChatColor.GRAY +"Bilet alan kimse yok!");
                    } else {
                        RouletteMenus.fieldsMenu(player);
                    }
                    break;
                case NETHER_STAR:
                    RouletteMenus.settingsMenu(player);
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Satın Al?")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case GREEN_WOOL:
                    if (MRoulette.getEconomy().getBalance(player) < 1000) {
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "Bakiyen 1000$ Büyük Olmalı!");
                        return;
                    }
                    if (plugin.roulettePlayers.keySet().size() > 45) {
                        player.sendMessage(ChatColor.RED + "Sana Yer Kalmadı :)");
                        return;
                    }
                    if (plugin.roulettePlayers.containsKey(player)) {
                        player.closeInventory();
                        player.sendMessage(ChatColor.RED + "Zaten bilet almışsın!");
                        return;
                    }
                    MRoulette.getEconomy().withdrawPlayer(player, 1000);
                    player.sendMessage(ChatColor.GREEN+ "Bilet aldın, bol şans!");
                    player.closeInventory();
                    plugin.roulettePlayers.put(player, 1000.0);
                    break;
                case RED_WOOL:
                    RouletteMenus.openMainMenu(player);
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Bilet alan oyuncular!")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ARROW:
                    RouletteMenus.openMainMenu(player);
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Ayarlar")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case ARROW:
                    RouletteMenus.openMainMenu(player);
                    break;
                case MAP:
                    if(!plugin.roulettePlayers.containsKey(player)) {
                        player.closeInventory();
                        player.sendTitle(ChatColor.GREEN +"Senin Bir Biletin Yok!","");
                    } else {
                        RouletteMenus.openConfirmMenu(player);
                    }
                    break;
            }
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW +"Eminmisin?")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case GREEN_WOOL:
                    MRoulette.getEconomy().depositPlayer(player,500);
                    player.sendTitle(ChatColor.GREEN +"Geri Çekildin!","");
                    player.closeInventory();
                    plugin.roulettePlayers.remove(player);
                    break;
                case RED_WOOL:
                    RouletteMenus.openMainMenu(player);
                    break;
            }
        }

    }

}
