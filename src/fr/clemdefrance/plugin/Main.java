package fr.clemdefrance.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new NovaEventListener(this), this);
        
        this.saveDefaultConfig();
        this.reloadConfig();
   
        
        getLogger().info("NovaTest activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("NovaTest désactivé !");
    }
}
