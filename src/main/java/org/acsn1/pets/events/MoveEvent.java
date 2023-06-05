package org.acsn1.pets.events;

import org.acsn1.pets.Pets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(!(Pets.getInstance().getPPetManager().hasPet(player.getUniqueId()))) return;
        if(!Pets.getInstance().getPPetManager().getLocSaves().containsKey(player.getUniqueId())) return;
        if(event.getFrom().getYaw() == event.getTo().getYaw()) return;
        if(event.getFrom().getPitch() == event.getTo().getPitch()) return;

        Pets.getInstance().getPPetManager().getLocSaves().put(player.getUniqueId(), player.getLocation().add(1.0,0,0.7));
    }

}
