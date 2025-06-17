package me.badawy.teleportationplugin.homes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class HomeEvents implements Listener {
    private HomeDBManger homeDataBaseAPI;
    public HomeEvents(HomeDBManger homeDataBaseAPI) {
        this.homeDataBaseAPI = homeDataBaseAPI;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        UUID uuid = event.getPlayer().getUniqueId();

        homeDataBaseAPI.loadAllPlayerHomes(uuid);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        homeDataBaseAPI.clearPlayerHomes(uuid);
    }
}