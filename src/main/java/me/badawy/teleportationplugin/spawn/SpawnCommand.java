package me.badawy.teleportationplugin.spawn;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Usage;

@Command("spawn")
public class SpawnCommand {

    @Usage
    public void usage(BukkitSource source) {
        if(source.isConsole()) {
            return;
        }

            if(SpawnUtilities.cooldownTimeLeft(source.asPlayer().getUniqueId()) > 0){
            source.reply(SpawnUtilities.spawnCooldownMessage(source.asPlayer().getUniqueId()));
            return;
        }
        source.asPlayer().teleport(SpawnUtilities.getSpawn(source.asPlayer()));
        SpawnUtilities.setCooldown(source.asPlayer().getUniqueId());
        source.reply(SpawnUtilities.spawnMessage());
    }
}
