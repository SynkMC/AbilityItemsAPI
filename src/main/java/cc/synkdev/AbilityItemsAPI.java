package cc.synkdev;

import cc.synkdev.items.AbilityItem;
import cc.synkdev.items.Listener;
import cc.synkdev.synkLibs.bukkit.SynkLibs;
import cc.synkdev.synkLibs.bukkit.Utils;
import cc.synkdev.synkLibs.components.SynkPlugin;
import lombok.Getter;
import lombok.Setter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbilityItemsAPI extends JavaPlugin implements SynkPlugin {
    @Getter private static AbilityItemsAPI instance;
    @Getter @Setter static Boolean first = true;
    public static List<AbilityItem> pluginsItems = new ArrayList<>();
    @Override
    public void onEnable() {
        instance = this;
        new Metrics(this, 23014);

        Bukkit.getPluginManager().registerEvents(new Listener(), this);
    }
    @Override
    public void onDisable() {

    }

    @Override
    public String name() {
        return "AbilityItemsAPI";
    }

    @Override
    public String ver() {
        return "1.3";
    }

    @Override
    public String dlLink() {
        return "https://modrinth.com/plugin/abilityitemsapi";
    }

    @Override
    public String prefix() {
        return ChatColor.translateAlternateColorCodes('&', "&8[&6AbilityItemsAPI&8] » &r");
    }

    @Override
    public String lang() {
        return null;
    }

    @Override
    public Map<String, String> langMap() {
        return null;
    }
}