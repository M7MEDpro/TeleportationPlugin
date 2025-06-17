package me.badawy.teleportationplugin.homes;

import me.badawy.teleportationplugin.DatabaseAPI;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.UUID;

public class HomeEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    UUID uuid = event.getPlayer().getUniqueId();
        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            ArrayList<String> homeNames = new ArrayList<>();
            String uuidKey = uuid.toString();

            for (Document doc : DatabaseAPI.teleportationcollection.find()) {
                if (doc.containsKey(uuidKey)) {
                    String homename = doc.getString(uuidKey);
                    if (homename != null) {
                        homeNames.add(homename);
                    }
                }
            }
            for (String s : homeNames ) {
             HomeDataBaseAPI.CraftObjectHome(uuid, s);
            }

        });
    }
@EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

    Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
        ArrayList<String> homeNames = new ArrayList<>();
        String uuidKey = uuid.toString();

        for (Document doc : DatabaseAPI.teleportationcollection.find()) {
            if (doc.containsKey(uuidKey)) {
                String homename = doc.getString(uuidKey);
                if (homename != null) {
                    homeNames.add(homename);
                }
            }
        }
        for (String s : homeNames ) {
            HomeDataBaseAPI.playerhomes.remove(KeysHome.maphomekey(uuid, s));
        }

    });
}
}
