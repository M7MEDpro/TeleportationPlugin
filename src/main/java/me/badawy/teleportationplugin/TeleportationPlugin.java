package me.badawy.teleportationplugin;

import me.badawy.teleportationplugin.homes.HomeDBManger;
import me.badawy.teleportationplugin.homes.HomeEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportationPlugin extends JavaPlugin {
    private static TeleportationPlugin instance;
    private HomeDBManger homeDataBaseAPI;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.instance = this;
        Bukkit.getPluginManager().registerEvents(new HomeEvents(homeDataBaseAPI), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static TeleportationPlugin getInstance() {
        return instance;
    }
}
