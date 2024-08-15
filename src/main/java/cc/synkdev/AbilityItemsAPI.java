package cc.synkdev;

import cc.synkdev.items.AbilityItem;
import cc.synkdev.items.Listener;
import cc.synkdev.synkLibs.Lang;
import cc.synkdev.synkLibs.SynkLibs;
import cc.synkdev.synkLibs.Utils;
import lombok.Getter;
import lombok.Setter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AbilityItemsAPI extends JavaPlugin {
    @Getter private static AbilityItemsAPI instance;
    @Getter @Setter static Boolean first = true;
    public static List<AbilityItem> pluginsItems = new ArrayList<>();
    public Lang lang;
    @Override
    public void onEnable() {
        instance = this;
        lang = new Lang(this);
        new Metrics(this, 23014);


        new Utils().checkUpdate("AbilityItemsAPI", "1.0", "");

        Bukkit.getPluginManager().registerEvents(new Listener(), this);

        //if (!Bukkit.getPluginManager().isPluginEnabled("SynkLibs")) Bukkit.getPluginManager().enablePlugin(new SynkLibs());
    }
    @Override
    public void onDisable() {

    }
}