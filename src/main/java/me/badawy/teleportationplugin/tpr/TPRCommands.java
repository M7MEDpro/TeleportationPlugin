package me.badawy.teleportationplugin.tpr;

import dev.velix.imperat.BukkitSource;
import dev.velix.imperat.annotations.Command;
import dev.velix.imperat.annotations.Dependency;
import dev.velix.imperat.annotations.Named;
import me.badawy.teleportationplugin.TeleportationPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TPRCommands {
    @Dependency
    private TPRUtilities tprUtilities;
    @Command("tpr")
    public void tpr(BukkitSource source,@Named("target") Player target) {
        if(source.isConsole())return;
        TPR object = new TPR(source.asPlayer().getUniqueId(), target.getUniqueId());
        List<UUID> templist = new ArrayList<>();
        templist.add(source.asPlayer().getUniqueId());
        templist.add(target.getUniqueId());
        TPRUtilities.requestMap.put(templist, object);
        source.reply(TPRUtilities.getMessageSend(target.getName()));
        target.sendMessage(TPRUtilities.getMessageSendTo(source.asPlayer().getName()));
        Bukkit.getScheduler().runTaskLater(TeleportationPlugin.getInstance(),()->{
            if (!TPRUtilities.requestMap.containsKey(templist)) return;
            TPRUtilities.requestMap.remove(templist);
            source.reply(TPRUtilities.getMessageExpireSender(target.getName()));
            target.sendMessage(TPRUtilities.getMessageExpire(source.asPlayer().getName()));

        },20L*TPRUtilities.expiretime());

    }
    @Command("tpa")
    public void tpa(BukkitSource source, @Named("target") Player target) {
        if (source.isConsole()) return;

        UUID senderUUID = source.asPlayer().getUniqueId();
        UUID targetUUID = target.getUniqueId();
        List<UUID> templist = new ArrayList<>();
        templist.add(senderUUID);
        templist.add(targetUUID);

        if (TPRUtilities.cooldownMap.containsKey(senderUUID)) {
            long lastUsed = TPRUtilities.cooldownMap.get(senderUUID);
            long now = System.currentTimeMillis();
            long secondsLeft = (lastUsed + TPRUtilities.cooldown() * 1000 - now) / 1000;
            if (secondsLeft > 0) {
                source.reply(TPRUtilities.getMessageCooldown(secondsLeft));
                return;
            }
        }

        if (TPRUtilities.requestMap.containsKey(templist)) {
            TPR object = TPRUtilities.requestMap.get(templist);
            TPRUtilities.requestMap.remove(templist);
            target.teleport(source.asPlayer().getLocation());
            source.reply(TPRUtilities.getMessageAccept(target.getName()));
            target.sendMessage(TPRUtilities.getMessageAcceptSender(source.asPlayer().getName()));

            TPRUtilities.cooldownMap.put(senderUUID, System.currentTimeMillis());
        } else {
            source.reply(TPRUtilities.getMessageNoRequest(target.getName()));
        }
    }

    @Command("tpc")
    public void tpc(BukkitSource source, @Named("target") Player target) {
        if (source.isConsole()) return;

        List<UUID> templist = new ArrayList<>();
        templist.add(source.asPlayer().getUniqueId());
        templist.add(target.getUniqueId());

        if (!TPRUtilities.requestMap.containsKey(templist)) {
            source.reply(TPRUtilities.getMessageNoRequest(target.getName()));
            return;
        }

        TPRUtilities.requestMap.remove(templist);
        source.reply(TPRUtilities.getMessageCancel(target.getName()));
        target.sendMessage(TPRUtilities.getMessageCancelSender(source.asPlayer().getName()));
    }

}

