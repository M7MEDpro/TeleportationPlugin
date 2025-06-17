package me.badawy.teleportationplugin.homes;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Named;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HomeCommand {
    private HomeDBManger homeDBManger;
    private KeysHome keysHome;
    @Command("sethome")
    public void sethome(BukkitSource source, @Named("homename") String homename) {

        if (!(source instanceof Player)) {
            source.reply("You must be a player to use this command!");
            return;
        }

            UUID uuid = source.asPlayer().getUniqueId();
        if(HomeConfigManger.homesperplayer.get(uuid) >= HomeConfigManger.maxHomes) {
            source.reply(HomeConfigManger.maxHomeMessage);
            return;
        }

        homeDBManger.CreateHomeDataBase(source.asPlayer(),homename);
    }
    @Command("home")
    public void home(BukkitSource source, String homename) {
        if (!(source instanceof Player)) {
            source.reply("You must be a player to use this command!");
            return;
        }
        UUID uuid = source.asPlayer().getUniqueId();
        String homeKey = keysHome.maphomekey(uuid, homename);
        Bukkit.getScheduler().runTaskAsynchronously(TeleportationPlugin.getInstance(), () -> {
           HomeObject homeObject = HomeDBManger.playerhomes.get(homeKey);
           if (homeObject == null) {
               source.reply(HomeConfigManger.noHomeMessage);
               return;
           }
           ((Player) source).teleport(homeObject.getLocation());
           source.reply(HomeConfigManger.teleportHomeMessage);
        });
    }
    @Command("rmHome")
    public void removeHome(BukkitSource source, String homename) {
        if (!(source instanceof Player)) {
            source.reply("You must be a player to use this command!");
        }
        homeDBManger.deleteHomeDataBase(source.asPlayer(),homename);
        source.reply(HomeConfigManger.deleteHomeMessage);
    }
}
