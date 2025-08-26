package fr.clemdefrance.plugin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NovaEventListener implements Listener {

    private final Main plugin;

    public NovaEventListener(Main main) {
    	this.plugin = main;
    }


	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();

        ItemStack customswar = new ItemStack(Material.COMPASS, 1);
        ItemMeta customM = customswar.getItemMeta();
        customM.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cWarzone"));
        customM.setLore(Arrays.asList("Warzone1", "Warzone2", "Warzone3"));
        customM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
        customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        customswar.setItemMeta(customM);

        player.getInventory().setItem(8, customswar);
        player.updateInventory();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if (it == null) return;

        if (it.getType() == Material.COMPASS &&
            it.hasItemMeta() &&
            it.getItemMeta().hasDisplayName() &&
            it.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&cWarzone"))) {

            Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_GRAY + "Warzone");

            ItemStack yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
            ItemStack orange = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
            ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);

            ItemMeta yellowM = yellow.getItemMeta();
            ItemMeta orangeM = orange.getItemMeta();
            ItemMeta redM = red.getItemMeta();

            yellowM.setDisplayName("Warzone 1");
            orangeM.setDisplayName("Warzone 2");
            redM.setDisplayName("Warzone 3");

            yellow.setItemMeta(yellowM);
            orange.setItemMeta(orangeM);
            red.setItemMeta(redM);

            inv.setItem(11, yellow);
            inv.setItem(13, orange);
            inv.setItem(15, red);

            player.openInventory(inv);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory invv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if (current == null) return;

        if (event.getView().getTitle().equals(ChatColor.DARK_GRAY + "Warzone")) {
            Location loc1 = new Location(player.getWorld(),
                    plugin.getConfig().getInt("location.warzone1.x"),
                    plugin.getConfig().getInt("location.warzone1.y"),
                    plugin.getConfig().getInt("location.warzone1.z"));
            Location loc2 = new Location(player.getWorld(),
                    plugin.getConfig().getInt("location.warzone2.x"),
                    plugin.getConfig().getInt("location.warzone2.y"),
                    plugin.getConfig().getInt("location.warzone2.z"));
            Location loc3 = new Location(player.getWorld(),
                    plugin.getConfig().getInt("location.warzone3.x"),
                    plugin.getConfig().getInt("location.warzone3.y"),
                    plugin.getConfig().getInt("location.warzone3.z"));

            if (current.getType() == Material.YELLOW_STAINED_GLASS_PANE) {
                player.teleport(loc1);
                player.closeInventory();
                player.sendTitle(ChatColor.RED + "Warzone 1", "", 10, 70, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            } else if (current.getType() == Material.ORANGE_STAINED_GLASS_PANE) {
                player.teleport(loc2);
                player.closeInventory();
                player.sendTitle(ChatColor.RED + "Warzone 2", "", 10, 70, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

            } else if (current.getType() == Material.RED_STAINED_GLASS_PANE) {
                player.teleport(loc3);
                player.closeInventory();
                player.sendTitle(ChatColor.RED + "Warzone 3", "", 10, 70, 20);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            }
        }
    }
}
