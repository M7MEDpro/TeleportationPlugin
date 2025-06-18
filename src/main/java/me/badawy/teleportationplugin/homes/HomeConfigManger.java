package me.badawy.teleportationplugin.homes;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeConfigManger {

    public static Map<UUID, Integer> homesperplayer = new HashMap<>();

    public static int getMaxHomes() {
        return TeleportationPlugin.getInstance().getConfig().getInt("home.max-homes", 3);
    }

    public static String setHomeMessage = getColored("home.messages.set-home", "&aYour home has been set.");
    public static String maxHomeMessage = getColored("home.messages.max-home", "&cYou have reached the max.");
    public static String teleportHomeMessage = getColored("home.messages.teleport-home", "&bTeleporting to your home...");
    public static String noHomeMessage = getColored("home.messages.no-home", "&eYou haven't set a home with that name yet.");
    public static String deleteHomeMessage = getColored("home.messages.delete-home", "&cYou have deleted home");

    public static long homecooldown = TeleportationPlugin.getInstance().getConfig().getLong("home.teleport-home.cooldown", 30L);

    public static String getCooldownMessage(UUID uuid, long secondsLeft) {
        String raw = TeleportationPlugin.getInstance().getConfig().getString(
                "home.teleport-home.cooldown-message",
                "&cYou are on cooldown. Please wait %seconds% seconds."
        );
        return ChatColor.translateAlternateColorCodes('&', raw.replace("%seconds%", String.valueOf(secondsLeft)));
    }

    private static String getColored(String path, String def) {
        String val = TeleportationPlugin.getInstance().getConfig().getString(path, def);
        return ChatColor.translateAlternateColorCodes('&', val);
    }
}
