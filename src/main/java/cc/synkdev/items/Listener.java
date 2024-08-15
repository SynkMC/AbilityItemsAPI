package cc.synkdev.items;

import cc.synkdev.AbilityItemsAPI;
import cc.synkdev.synkLibs.Utils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * EventHandler class listening to when an item gets used.
 */
public class Listener implements org.bukkit.event.Listener { 
    List<AbilityItem> items;
    AbilityItemsAPI core = AbilityItemsAPI.getInstance();
    public Listener() {
        this.items = new ArrayList<>(core.pluginsItems);
    }

    @EventHandler
    public void interact(PlayerInteractEvent event) {
        AbilityItem item = relatedItem(event.getItem());
        if (item != null) {
            if (item.getPermission() != null) {
                if (!event.getPlayer().hasPermission(item.getPermission())) return;
            }
            item.getEvent().execute(event);
        }
    }

    @EventHandler
    public void click(InventoryClickEvent event) {
        AbilityItem item = relatedItem(event.getCurrentItem());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    @EventHandler
    public void drag(InventoryDragEvent event) {
        AbilityItem item = relatedItem(event.getCursor());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    @EventHandler
    public void moveItem(InventoryMoveItemEvent event) {
        AbilityItem item = relatedItem(event.getItem());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        AbilityItem item = relatedItem(event.getItemDrop().getItemStack());
        if (item != null) {
            event.setCancelled(item.getLocked() || !item.getAllowDrop());
        }
    }

    @EventHandler
    public void pickup(InventoryPickupItemEvent event) {
        AbilityItem item = relatedItem(event.getItem().getItemStack());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    @EventHandler
    public void creative(InventoryCreativeEvent event) {
        AbilityItem item = relatedItem(event.getCurrentItem());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    @EventHandler
    public void creative(PlayerSwapHandItemsEvent event) {
        AbilityItem item = relatedItem(event.getMainHandItem());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
        item = relatedItem(event.getOffHandItem());
        if (item != null) {
            event.setCancelled(item.getLocked());
        }
    }

    public AbilityItem relatedItem(ItemStack is) {
        if (is == null) {
            return null;
        }
        for (AbilityItem item : items) {
            ItemStack its = item.getItemStack();
            if (areItemStacksEqual(its, is)) {
                return item;
            }
        }
        return null;
    }

    private boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
        if (is1 == null || is2 == null) {
            return false;
        }
        NBTItem nbti1 = new NBTItem(is1);
        NBTItem nbti2 = new NBTItem(is2);

        if (!nbti1.hasTag("abilityId") || !nbti2.hasTag("abilityId")) return false;

        return nbti1.getString("abilityId").equals(nbti2.getString("abilityId"));
    }
}
