package me.badawy.teleportationplugin.tp;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Named;
import dev.velix.imperat.annotations.Permission;
import dev.velix.imperat.annotations.Usage;
import org.bukkit.entity.Player;

@Command("tp")
@Permission("tpplugin.tp")
public class TPCommand {
    @Usage
    public void tp(BukkitSource source, @Named("target")Player target) {
        if(source.isConsole()) return;
        TPUtilities.teleport(target,source.asPlayer());
        source.reply(TPUtilities.tpmessage(target));
    }
}
