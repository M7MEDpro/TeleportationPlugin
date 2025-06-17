package me.badawy.teleportationplugin.homes;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Named;
import dev.velix.imperat.annotations.SubCommand;

import java.util.UUID;

@Command("home")
public class HomeCommand {

    @SubCommand("create")
   public void CreateHome(BukkitSource source, @Named("homename") String homename) {
        UUID uuid = source.asPlayer().getUniqueId();
        if(HomeConfigManger.homesperplayer.get(uuid) >= HomeConfigManger.max_homes) {

            return;
        }
    }
}
