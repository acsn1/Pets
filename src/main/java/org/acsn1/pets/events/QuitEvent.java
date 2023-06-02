package org.acsn1.pets.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        for (Entity entities : event.getPlayer().getLocation().getWorld().getEntities()) {
            if (entities.getCustomName() != null) {
                if (entities.getCustomName().equalsIgnoreCase(event.getPlayer().getName() + "'s Pet")) {
                    entities.remove();
                    }
            }
        }
    }

}
