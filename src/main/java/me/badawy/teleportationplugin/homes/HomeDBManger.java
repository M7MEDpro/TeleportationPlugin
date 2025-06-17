
package me.badawy.teleportationplugin.homes;

import com.mongodb.client.model.Filters;
import me.badawy.teleportationplugin.DatabaseConnction;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class HomeDBManger {
    private KeysHome keysHome;
    public static Map<String, HomeObject> playerhomes = new ConcurrentHashMap<>();
    public HomeDBManger(KeysHome keysHome) {
        this.keysHome = keysHome;
    }
    public void CreateHomeDataBase(Player player, String homename) {

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
        String homeKey = keysHome.maphomekey(uuid, homename);

        playerhomes.put(homeKey, homeobject);

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            try {
                DatabaseConnction.teleportationcollection.insertOne(home);
            } catch (Exception e) {
                playerhomes.remove(homeKey);
            }
        });
       int last = HomeConfigManger.homesperplayer.get(uuid);
               HomeConfigManger.homesperplayer.put(uuid, ++last);
    }

    public void deleteHomeDataBase(Player player, String homename) {
        UUID uuid = player.getUniqueId();
        String homeKey = keysHome.maphomekey(uuid, homename);

        playerhomes.remove(homeKey);

        Bson filter = Filters.and(
                Filters.eq("uuid", uuid.toString()),
                Filters.eq("homename", homename)
        );

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {

                DatabaseConnction.teleportationcollection.deleteOne(filter);

        });
        int last = HomeConfigManger.homesperplayer.get(uuid);
        HomeConfigManger.homesperplayer.put(uuid, --last);
    }

    public void CraftObjectHome(UUID uuid, String homename) {
        String homeKey = keysHome.maphomekey(uuid, homename);

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {

                Bson filter = Filters.and(
                        Filters.eq("uuid", uuid.toString()),
                        Filters.eq("homename", homename)
                );

                Document doc = DatabaseConnction.teleportationcollection.find(filter).first();

                if (doc != null) {
                    double x = doc.getDouble("x");
                    double y = doc.getDouble("y");
                    double z = doc.getDouble("z");
                    String world = doc.getString("worldname");

                    HomeObject home = new HomeObject(uuid, homename, x, y, z, world);

                    playerhomes.put(homeKey, home);
                }

        });
    }

    public void loadAllPlayerHomes(UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {

                Bson filter = Filters.eq("uuid", uuid.toString());

                for (Document doc : DatabaseConnction.teleportationcollection.find(filter)) {
                    String homename = doc.getString("homename");
                    double x = doc.getDouble("x");
                    double y = doc.getDouble("y");
                    double z = doc.getDouble("z");
                    String world = doc.getString("worldname");

                    HomeObject home = new HomeObject(uuid, homename, x, y, z, world);
                    String homeKey = keysHome.maphomekey(uuid, homename);

                    playerhomes.put(homeKey, home);
                }

        });
    }

    public void clearPlayerHomes(UUID uuid) {
        String uuidStr = uuid.toString();
        playerhomes.entrySet().removeIf(entry -> entry.getKey().startsWith(uuidStr + "_"));
    }
        public HomeObject getHomeObject(UUID uuid, String homename) {
            String homeKey = keysHome.maphomekey(uuid, homename);
            return playerhomes.get(homeKey);
        }
}