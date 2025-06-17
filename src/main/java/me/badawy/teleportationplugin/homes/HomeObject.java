package me.badawy.teleportationplugin.homes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class HomeObject {
    private UUID UserID;
    private String homeName;
    private Double x;
    private Double y;
    private Double z;
    private String wordname;
    public HomeObject(UUID UserID, String homeName, Double x, Double y, Double z, String wordname) {
        this.UserID = UserID;
        this.homeName = homeName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.wordname = wordname;
    }
    public String getHomeName() {
        return homeName;
    }

    public Location getLocation() {
        World world = Bukkit.getServer().getWorld(wordname);
        Location loc = new Location(world, x, y, z);
        return loc;
    }

}
