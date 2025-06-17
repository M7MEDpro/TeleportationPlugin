package me.badawy.teleportationplugin.homes;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Dependency;
import dev.velix.imperat.annotations.Named;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class HomeCommand {
    private static Map<UUID, Long> cooldowns = new HashMap<>();

    @Dependency
    private HomeDBManger homeDBManger;
    @Command("sethome")
    public void sethome(BukkitSource source, @Named("homename") String homename) {

        if (source.isConsole()) {
            source.reply("You must be a player to use this command!");
            return;
        }

        UUID uuid = source.asPlayer().getUniqueId();
        Set<HomeObject> playerHomes = homeDBManger.getPlayerHomes(uuid);
        if(playerHomes.size() >= HomeConfigManger.getMaxHomes()) {
            source.reply(HomeConfigManger.maxHomeMessage);
            return;
        }

        homeDBManger.createHomeDataBase(source.asPlayer(),homename);
    }
    @Command("home")
    public void home(BukkitSource source, String homename) {
        if (source.isConsole()) {
            source.reply("You must be a player to use this command!");
            return;
        }
        UUID uuid = source.asPlayer().getUniqueId();

        long currentTime = System.currentTimeMillis();
        if (cooldowns.containsKey(uuid)) {
            long lastUsed = cooldowns.get(uuid);
            long timeLeft = HomeConfigManger.homecooldown * 1000 - (currentTime - lastUsed);
            if (timeLeft > 0) {
                long secondsLeft = timeLeft / 1000;
                source.reply(HomeConfigManger.getCooldownMessage(uuid, secondsLeft));
                return;
            }
        }

        HomeObject homeObject = homeDBManger.getHomeObject(uuid, homename);
        if (homeObject == null) {
           source.reply(HomeConfigManger.noHomeMessage);
           return;
        }
         source.asPlayer().teleport(homeObject.getLocation());
        source.reply(HomeConfigManger.teleportHomeMessage);

    }
    @Command("rmHome")
    public void removeHome(BukkitSource source, String homename) {
        if (source.isConsole()) {
            source.reply("You must be a player to use this command!");
        }
        homeDBManger.deleteHomeDataBase(source.asPlayer(),homename);
        source.reply(HomeConfigManger.deleteHomeMessage);
    }
}
