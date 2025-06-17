package me.badawy.teleportationplugin.homes;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Dependency;
import dev.velix.imperat.annotations.Named;

import java.util.Set;
import java.util.UUID;

public class HomeCommand {
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

        System.out.println("Home " + homename + " has " + playerHomes.size() + " homes");
        System.out.println("MAX HOMES= " + HomeConfigManger.getMaxHomes());
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
