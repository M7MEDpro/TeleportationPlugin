package me.badawy.teleportationplugin.spawn;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpawnUtilities {
    private static Map<UUID, Long> cooldowns = new HashMap<>();
    public static Location getSpawn(Player player) {
        World world = player.getWorld();
        Location loc = world.getSpawnLocation();
        return loc;
    }
    public static String spawnMessage() {

        return ChatColor.translateAlternateColorCodes('&',  TeleportationPlugin.getInstance().getConfig().getString("spawn.messages.teleport", "&bTeleporting to spawn..."));
    }

    public static boolean isCooldown(UUID uuid) {

        long currentTime = System.currentTimeMillis();
        if (cooldowns.containsKey(uuid)) {
            long lastUsed = cooldowns.get(uuid);
            long timeLeft = TeleportationPlugin.getInstance().getConfig().getLong("spawn.cooldown",30) * 1000 - (currentTime - lastUsed);
            if (timeLeft > 0) {
                return true;
            }
        }
        return false;
    }
    public static String spawnCooldownMessage(UUID uuid) {
        long timeLeft = cooldownTimeLeft(uuid);

        return ChatColor.translateAlternateColorCodes('&',  TeleportationPlugin.getInstance().getConfig().getString("spawn.messages.cooldown", "&cYou are on cooldown. Please wait %seconds% seconds.").replace("%seconds%", String.valueOf(timeLeft)));
    }

    public static long cooldownTimeLeft(UUID uuid) {
            long currentTime = System.currentTimeMillis();
            if (cooldowns.containsKey(uuid)) {
                long lastUsed = cooldowns.get(uuid);
                long timeLeft = TeleportationPlugin.getInstance().getConfig().getLong("spawn.cooldown",30) * 1000 - (currentTime - lastUsed);
                if (timeLeft > 0) {
                    return timeLeft/1000;
                }
            }
        return 0;
    }
    public static void setCooldown(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis());
    }
}
