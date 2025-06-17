package me.badawy.teleportationplugin.tp;

import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TPUtilities {
    public static Location getLocationOfPlayer(Player player){
        return player.getLocation();
    }
    public static void teleport(Player player1,Player toplayer){
        Location loc1 = getLocationOfPlayer(toplayer);
        player1.teleport(loc1);
    }
public  static String tpmessage(Player target){
    String rawMessage = TeleportationPlugin.getInstance().getConfig().getString(
            "tp.messages.teleport-to-player",
            "&cTeleported to %target%"
    );

    return ChatColor.translateAlternateColorCodes('&', rawMessage.replace("%target%", target.getName()));
}
}
