package com.harryeakins.mathplugin;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MathPlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EventListener(), this);

        EarnCommand ec = new EarnCommand();
        getCommand("earn").setExecutor(ec);
        getCommand("ans").setExecutor(ec);
        getCommand("coins").setExecutor(ec);

        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }
}
