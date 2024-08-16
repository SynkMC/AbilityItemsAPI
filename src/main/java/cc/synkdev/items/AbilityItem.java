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
     * Initialize the item
     * @param id The ID you want to give to the item
     */
    public AbilityItem(String id) {
        this.setId("id");
    }
    public static AbilityItem newItem(String id) {
        return new AbilityItem(id);
    }

    /**
     * Set what itemStack will be represented by the AbilityItem
     * @param item The ItemStack
     * @return
     */
    public AbilityItem from(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("abilityId", this.getId());
        nbtItem.applyNBT(item);
        this.setItemStack(item);
        return this;
    }

    /**
     * Stops the item from being moved from the slot it was in originally (except with openInv, death or /clear
     * @return
     */
    public AbilityItem lockInSlot() {
        this.setLocked(true);
        return this;
    }

    /**
     * Stops the item from being dropped to the ground
     * @return
     */
    public AbilityItem denyDrop() {
        this.setAllowDrop(false);
        return this;
    }

    /**
     * Sets what happens when the item is clicked
     * @param event An example event for you to work on
     * @return
     */
    public AbilityItem setClickEvent(ClickActions<PlayerInteractEvent> event) {
        this.setEvent(event);
        return this;
    }

    /**
     * Set the stack size of the item
     * @param quantity Stack size
     * @return
     */
    public AbilityItem setQuantity(int quantity) {
        this.getItemStack().setAmount(quantity);
        return this;
    }

    /**
     * Sets the permission required to use the item.
     * @param permission
     * @return
     */
    public AbilityItem setPermission(String permission) {
        this.setPermission(permission);
        return this;
    }

    /**
     * Submit the item to the plugin
     * @return
     */
    public AbilityItem build() {
        AbilityItemsAPI.pluginsItems.add(this);
        return this;
    }

    /**
     * Give the item to a player
     * @param p The player
     */
    public void give(Player p) {
        p.getInventory().addItem(this.getItemStack());
    }
}
