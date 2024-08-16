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

public class AbilityItemsAPI extends JavaPlugin implements SynkPlugin {
    @Getter private static AbilityItemsAPI instance;
    @Getter @Setter static Boolean first = true;
    public static List<AbilityItem> pluginsItems = new ArrayList<>();
    @Override
    public void onEnable() {
        instance = this;
        new Metrics(this, 23014);

        SynkLibs.setSpl(this);
        Utils.checkUpdate(this, this);

        Bukkit.getPluginManager().registerEvents(new Listener(), this);

        //if (!Bukkit.getPluginManager().isPluginEnabled("SynkLibs")) Bukkit.getPluginManager().enablePlugin(new SynkLibs());
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
        return "1.2";
    }

    @Override
    public String dlLink() {
        return "https://modrinth.com/plugin/abilityitemsapi";
    }

    @Override
    public String prefix() {
        return ChatColor.translateAlternateColorCodes('&', "&8[&6AbilityItemsAPI&8] » &r");
    }
}