
package me.badawy.teleportationplugin.homes;

import com.mongodb.client.model.Filters;
import me.badawy.teleportationplugin.DatabaseConnction;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;


public class HomeDBManger {

    private final Map<UUID, Set<HomeObject>> playerHomes = new HashMap<>();

    public void createHomeDataBase(Player player, String homename) {

        UUID uuid = player.getUniqueId();
        Location loc = player.getLocation();
        String world = loc.getWorld().getName();
        double x = Math.round(loc.getX() * 100) / 100.0;
        double y = Math.round(loc.getY() * 100) / 100.0;
        double z = Math.round(loc.getZ() * 100) / 100.0;

        Document home = new Document()
                .append("uuid", uuid.toString())
                .append("homename", homename)
                .append("x", x)
                .append("y", y)
                .append("z", z)
                .append("worldname", world);

        HomeObject homeobject = new HomeObject(uuid, homename, x, y, z, world);
        playerHomes.compute(player.getUniqueId(), (k, oldHomes)-> {

            if(oldHomes == null) {
                Set<HomeObject> newStart = new HashSet<>();
                newStart.add(homeobject);
                return newStart;
            }

            oldHomes.add(homeobject);
            return oldHomes;
        });

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
            try {
                DatabaseConnction.teleportationcollection.insertOne(home);
            } catch (Exception e) {
                e.printStackTrace();
                playerHomes.computeIfPresent(player.getUniqueId(), (k, oldHomes)-> {
                    if(oldHomes.isEmpty()) {
                        return oldHomes;
                    }
                    oldHomes.remove(homeobject);
                    return oldHomes;

                });
            }
        });
    }

    public void deleteHomeDataBase(Player player, String homename) {
        UUID uuid = player.getUniqueId();
        playerHomes.computeIfPresent(player.getUniqueId(), (k, oldHomes)-> {
            oldHomes.removeIf((home)-> home.getHomeName().equalsIgnoreCase(homename));
            return oldHomes;
        });

        Bson filter = Filters.and(
                Filters.eq("uuid", uuid.toString()),
                Filters.eq("homename", homename)
        );

        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {

                DatabaseConnction.teleportationcollection.deleteOne(filter);

        });

    }

    public void loadAllPlayerHomes(UUID uuid) {
        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {

                Bson filter = Filters.eq("uuid", uuid.toString());


                Set<HomeObject> homes = new HashSet<>();
                for (Document doc : DatabaseConnction.teleportationcollection.find(filter)) {
                    String homename = doc.getString("homename");
                    double x = doc.getDouble("x");
                    double y = doc.getDouble("y");
                    double z = doc.getDouble("z");
                    String world = doc.getString("worldname");

                    HomeObject home = new HomeObject(uuid, homename, x, y, z, world);
                    System.out.println("Adding a home !");
                    homes.add(home);
                }
                playerHomes.put(uuid, homes);
        });
    }

    public void clearPlayerHomes(UUID uuid) {
        playerHomes.remove(uuid);
    }

    public HomeObject getHomeObject(UUID uuid, String homename) {
        Set<HomeObject> homes = playerHomes.get(uuid);
        if(homes == null) {
            return null;
        }

        for(HomeObject object : homes) {
            if(object.getHomeName().equalsIgnoreCase(homename)) {
                return object;
            }
        }
        return null;
    }

    public Set<HomeObject> getPlayerHomes(UUID uuid) {
        return playerHomes.getOrDefault(uuid, Collections.emptySet());
    }
}