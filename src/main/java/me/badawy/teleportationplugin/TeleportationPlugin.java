package me.badawy.teleportationplugin;

import dev.velix.imperat.BukkitImperat;
import me.badawy.teleportationplugin.homes.HomeCommand;
import me.badawy.teleportationplugin.homes.HomeDBManger;
import me.badawy.teleportationplugin.homes.HomeEvents;
import me.badawy.teleportationplugin.spawn.SpawnCommand;
import me.badawy.teleportationplugin.tp.TPCommand;
import me.badawy.teleportationplugin.tpr.TPRCommands;
import me.badawy.teleportationplugin.tpr.TPRUtilities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportationPlugin extends JavaPlugin {
    private static TeleportationPlugin instance;
    private HomeDBManger homeDataBaseAPI;
    private TPRUtilities tprUtilities;
    private BukkitImperat imperat;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        homeDataBaseAPI = new HomeDBManger();
        Bukkit.getPluginManager().registerEvents(new HomeEvents(homeDataBaseAPI), this);
        imperat = BukkitImperat.builder(this).dependencyResolver(HomeDBManger.class,()-> homeDataBaseAPI).dependencyResolver(TPRUtilities.class,()-> tprUtilities).build();
        imperat.registerCommand(new HomeCommand());
        imperat.registerCommand(new SpawnCommand());
        imperat.registerCommand(new TPCommand());
        imperat.registerCommand(new TPRCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static TeleportationPlugin getInstance() {
        return instance;
    }
}
