package me.badawy.teleportationplugin.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;
import java.util.UUID;

public class HomeObject {
    private final UUID userId;
    private final String homeName;
    private final double x, y ,z;
    private final String worldName;
    public HomeObject(UUID userId, String homeName, double x, double y, double z, String worldName) {
        this.userId = userId;
        this.homeName = homeName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

    public String getHomeName() {
        return homeName;
    }

    public Location getLocation() {
        World world = Bukkit.getServer().getWorld(worldName);
        return new Location(world, x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HomeObject that = (HomeObject) o;
        return Objects.equals(homeName, that.homeName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(homeName);
    }
}
