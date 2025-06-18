package me.badawy.teleportationplugin.tpr;

import java.util.UUID;

public class TPR {
    private static long cooldown = TPRUtilities.cooldown();
    private static long expiretime = TPRUtilities.expiretime();
    private UUID senderuuid;
    private UUID receiveruuid;
    public TPR(UUID senderuuid, UUID receiveruuid){
        this.senderuuid = senderuuid;
        this.receiveruuid = receiveruuid;
    }
    public UUID getSenderuuid() {
        return senderuuid;
    }
   public UUID getReceiveruuid() {
        return receiveruuid;
   }
}
