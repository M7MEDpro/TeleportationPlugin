package me.badawy.teleportationplugin.homes;

import com.mongodb.client.model.Filters;
import me.badawy.teleportationplugin.DatabaseAPI;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeDataBaseAPI {

    public static Map<String, HomeObject> playerhomes = new HashMap<>();

    public static void CreateHomeDataBase(Player player, String homename) {
        UUID uuid = player.getUniqueId();
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        Double x = Math.round(loc.getX() * 100) / 100.0;
        Double y = Math.round(loc.getY() * 100) / 100.0;
        Double z = Math.round(loc.getZ() * 100) / 100.0;

        Document home = new Document()
                .append("uuid", uuid.toString())
                .append("homename", homename)
                .append("x", x)
                .append("y", y)
                .append("z", z)
                .append("worldname", world);

        HomeObject homeobject = new HomeObject(uuid, homename, x, y, z, world);

        String homeKey = KeysHome.maphomekey(uuid, homename);
        playerhomes.put(homeKey, homeobject);

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            DatabaseAPI.teleportationcollection.insertOne(home);
        });
    }

    public static void deleteHomeDataBase(Player player, String homename) {
        UUID uuid = player.getUniqueId();
        String homeKey = KeysHome.maphomekey(uuid, homename);

        playerhomes.remove(homeKey);

        Document filter = new Document()
                .append("uuid", uuid.toString())
                .append("homename", homename);

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            DatabaseAPI.teleportationcollection.deleteOne(filter);
        });
    }

    public static void CraftObjectHome(UUID uuid, String homename) {
        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            Bson filter = Filters.and(
                    Filters.eq("uuid", uuid.toString()),
                    Filters.eq("homename", homename)
            );

            Document doc = DatabaseAPI.teleportationcollection.find(filter).first();

            if (doc != null) {
                double x = doc.getDouble("x");
                double y = doc.getDouble("y");
                double z = doc.getDouble("z");
                String world = doc.getString("worldname");

                HomeObject home = new HomeObject(uuid, homename, x, y, z, world);

                String homeKey = KeysHome.maphomekey(uuid, homename);

                Bukkit.getScheduler().runTask(TeleportationPlugin.getInstance(), () -> {
                    playerhomes.put(homeKey, home);
                });
            }
        });
    }
}
