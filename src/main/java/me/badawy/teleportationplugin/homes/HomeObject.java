package me.badawy.teleportationplugin.homes;

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

}
