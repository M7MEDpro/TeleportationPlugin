package me.badawy.teleportationplugin.homes;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeConfigManger {
    public static Map<UUID,Integer> homesperplayer = new HashMap<UUID,Integer>();

    public static int maxHomes = TeleportationPlugin.getInstance().getConfig().getInt("home.max-homes");
    public static String setHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.set-home"));
    public static String maxHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.max-home"));
    public static String teleportHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.teleport-home"));
    public static String noHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.no-home"));
    public static String deleteHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.delete-home"));
}