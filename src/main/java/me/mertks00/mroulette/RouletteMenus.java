package me.mertks00.mroulette;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RouletteMenus {

    private static MRoulette plugin;

    public RouletteMenus(MRoulette plugin) {
        RouletteMenus.plugin = plugin;
    }

    public static void openMainMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Rulet Menü");

        ItemStack buyItem = new ItemStack(Material.DIAMOND);
        ItemMeta buyItemMeta = buyItem.getItemMeta();
        buyItemMeta.setDisplayName(ChatColor.AQUA + "Satın Al?");
        buyItem.setItemMeta(buyItemMeta);

        ItemStack fieldsMenu = new ItemStack(Material.PAPER);
        ItemMeta fieldsMenuMeta = fieldsMenu.getItemMeta();
        fieldsMenuMeta.setDisplayName(ChatColor.YELLOW + "("+plugin.roulettePlayers.keySet().size()+"/45) " + "Satın Alanlar?");
        fieldsMenu.setItemMeta(fieldsMenuMeta);

        ItemStack settings = new ItemStack(Material.NETHER_STAR);
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(ChatColor.RED + "Ayarlar");
        settings.setItemMeta(settingsMeta);

        menu.setItem(11, buyItem);
        menu.setItem(13, fieldsMenu);
        menu.setItem(15, settings);

        player.openInventory(menu);

    }

    public static void boughtMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.YELLOW + "Satın Al?");

        ItemStack bought = new ItemStack(Material.GREEN_WOOL);
        ItemMeta boughtMeta = bought.getItemMeta();
        boughtMeta.setDisplayName(ChatColor.GREEN + "Satın Al: 1000$");
        bought.setItemMeta(boughtMeta);

        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.YELLOW + "Geri Dön?");
        cancel.setItemMeta(cancelMeta);

        menu.setItem(12, bought);
        menu.setItem(14, cancel);

        player.openInventory(menu);

    }

    public static void fieldsMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 54,ChatColor.AQUA + "Bilet alan oyuncular!");

        ArrayList<Player> menuList = new ArrayList<>(plugin.roulettePlayers.keySet());

            for(Player players : menuList) {

                ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
                SkullMeta headSkull = (SkullMeta) head.getItemMeta();
                headSkull.setOwningPlayer(players);
                headSkull.setDisplayName(players.getName());
                head.setItemMeta(headSkull);

                menu.addItem(head);

            }

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Geri Dön!");
        back.setItemMeta(backMeta);

        menu.setItem(53,back);

        player.openInventory(menu);

    }

    public static void settingsMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.RED + "Ayarlar");

        ItemStack remove = new ItemStack(Material.MAP);
        ItemMeta removeMeta = remove.getItemMeta();
        removeMeta.setDisplayName(ChatColor.YELLOW + "Bileti İade Et!");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Eğer iptal edersen paranın yarısı,");
        lore.add(ChatColor.GRAY + "geri verilicek kalarak şansını deneyebilirsin!");
        removeMeta.setLore(lore);
        remove.setItemMeta(removeMeta);

        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.RED + "Geri Dön!");
        back.setItemMeta(backMeta);

        menu.setItem(13, remove);
        menu.setItem(26, back);

        player.openInventory(menu);

    }

    public static void openConfirmMenu(Player player) {

        Inventory menu = Bukkit.createInventory(null, 27,ChatColor.YELLOW +"Eminmisin?");

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN +"Onayla!");
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "Vazgeç!");
        cancel.setItemMeta(cancelMeta);

        menu.setItem(12,confirm);
        menu.setItem(14,cancel);

        player.openInventory(menu);

    }

}
