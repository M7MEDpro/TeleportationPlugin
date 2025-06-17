package me.badawy.teleportationplugin.homes;

import java.util.UUID;

public class KeysHome {
    public static String GetUniqueKeyHome(UUID uuid, HomeObject homeobject) {
      return   uuid.toString()+homeobject.getHomeName();
    }
    public static String GetUniqueKeyHomeByOnlyName(UUID uuid, String homeobject) {
        return   uuid.toString()+homeobject;
    }
    public static String maphomekey(UUID uuid, String homename) {
        return uuid.toString() + "_" + homename;
    }
}
