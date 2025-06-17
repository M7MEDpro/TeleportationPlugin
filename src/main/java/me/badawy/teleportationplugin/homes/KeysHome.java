package me.badawy.teleportationplugin.homes;

import java.util.UUID;

public class KeysHome {
    public  String GetUniqueKeyHome(UUID uuid, HomeObject homeobject) {
      return   uuid.toString()+homeobject.getHomeName();
    }
    public  String GetUniqueKeyHomeByOnlyName(UUID uuid, String homeobject) {
        return   uuid.toString()+homeobject;
    }
    public  String maphomekey(UUID uuid, String homename) {
        return uuid.toString() + "_" + homename;
    }
}
