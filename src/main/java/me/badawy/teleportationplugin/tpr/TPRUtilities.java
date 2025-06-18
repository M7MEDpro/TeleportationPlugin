package me.badawy.teleportationplugin.tpr;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;

import java.util.*;


public class TPRUtilities {
    public static Map<List<UUID>, TPR> requestMap = new HashMap<>();
    public static Map<UUID, Long> cooldownMap = new HashMap<>();

    //config
public static long cooldown(){

    return TeleportationPlugin.getInstance().getConfig().getLong("tpr.cooldown",30);
}
    public static long expiretime(){

        return TeleportationPlugin.getInstance().getConfig().getLong("tpr.expiretime",30);
    }

    public static String getMessageSend(String target) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString("tpr.messages.send", "&b tp request has been send to %target%");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%target%", target);
    }

    public static String getMessageSendTo(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString("tpr.messages.send-to", "&b you have a tp request from %player%");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }

    public static String getMessageAccept(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString("tpr.messages.accept", "&b accept request from %player%");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }

    public static String getMessageCooldown(long secondsLeft) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString("tpr.messages.cooldown", "&cYou are on cooldown. Please wait %seconds% seconds.");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%seconds%", String.valueOf(secondsLeft));
    }

    public static String getMessageExpire(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString("tpr.messages.expire", "&b expired rquest from %player%");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }
    public static String getMessageNoRequest(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString(
                "tpr.messages.error-norequestsend",
                "&cError: %player% didn't send you a request"
        );
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }
    public static String getMessageExpireSender(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString(
                "tpr.messages.expiresender",
                "&b expired rquest send to %player%"
        );
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }

    public static String getMessageAcceptSender(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString(
                "tpr.messages.acceptsender",
                "&a%player% has accepted your request"
        );
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }
    public static String getMessageCancel(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString(
                "tpr.messages.cancel", "&cYou cancelled the teleport request to %player%");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }

    public static String getMessageCancelSender(String player) {
        String msg = TeleportationPlugin.getInstance().getConfig().getString(
                "tpr.messages.cancelsender", "&c%player% cancelled the teleport request");
        return ChatColor.translateAlternateColorCodes('&', msg).replace("%player%", player);
    }


}
