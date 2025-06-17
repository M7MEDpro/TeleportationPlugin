package me.badawy.teleportationplugin.homes;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeConfigManger {
    public static Map<UUID,Integer> homesperplayer = new HashMap<>();

    public static int getMaxHomes() {

        return TeleportationPlugin.getInstance().getConfig().getInt("home.max-homes");
    }
    public static String setHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.set-home"));
    public static String maxHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.max-home"));
    public static String teleportHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.teleport-home"));
    public static String noHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.no-home"));
    public static String deleteHomeMessage = ChatColor.translateAlternateColorCodes('&', TeleportationPlugin.getInstance().getConfig().getString("home.messages.delete-home"));
    public static long homecooldown = TeleportationPlugin.getInstance().getConfig().getLong("home.teleport-home.cooldown");


    public static String getCooldownMessage(UUID uuid ,long secondsLeft) {
        String raw = TeleportationPlugin.getInstance().getConfig()
                .getString("home.teleport-home.cooldown-message", "&cYou are on cooldown. Please wait %seconds% seconds.");
        return ChatColor.translateAlternateColorCodes('&', raw.replace("%seconds%", String.valueOf(secondsLeft)));
    }
}