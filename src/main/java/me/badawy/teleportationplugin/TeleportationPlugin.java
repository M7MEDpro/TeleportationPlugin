package me.badawy.teleportationplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportationPlugin extends JavaPlugin {
    private static TeleportationPlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
    this.instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static TeleportationPlugin getInstance() {
        return instance;
    }
}
