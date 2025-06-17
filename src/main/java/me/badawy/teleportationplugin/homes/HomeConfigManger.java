package me.badawy.teleportationplugin.homes;

import me.badawy.teleportationplugin.TeleportationPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeConfigManger {
    public static Map<UUID,Integer> homesperplayer = new HashMap<UUID,Integer>();
    public static  int max_homes = TeleportationPlugin.getInstance().getConfig().getInt("max-homes");

}
