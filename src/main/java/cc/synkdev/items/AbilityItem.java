package cc.synkdev.items;

import cc.synkdev.AbilityItemsAPI;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * The class representing Items you give abilities to
 */
@Getter @Setter
public class AbilityItem {
    ItemStack itemStack = null;
    Boolean locked = false;
    Boolean allowDrop = true;
    String id = null;
    String permission = null;
    ClickActions<PlayerInteractEvent> event;

    /**
     *
     * @param id Initialize the item
     */
    public AbilityItem(String id) {
        this.setId("id");
    }
    public static AbilityItem newItem(String id) {
        return new AbilityItem(id);
    }
    public AbilityItem from(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("abilityId", this.getId());
        nbtItem.applyNBT(item);
        this.setItemStack(item);
        return this;
    }
    public AbilityItem lockInSlot() {
        this.setLocked(true);
        return this;
    }
    public AbilityItem denyDrop() {
        this.setAllowDrop(false);
        return this;
    }
    public AbilityItem setClickEvent(ClickActions<PlayerInteractEvent> event) {
        this.setEvent(event);
        return this;
    }
    public AbilityItem setQuantity(int quantity) {
        this.getItemStack().setAmount(quantity);
        return this;
    }
    public AbilityItem setPermission(String permission) {
        this.setPermission(permission);
        return this;
    }
    public AbilityItem build() {
        AbilityItemsAPI.pluginsItems.add(this);
        return this;
    }

    public void give(Player p) {
        p.getInventory().addItem(this.getItemStack());
    }
}
