package org.acsn1.pets.events;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        PlayerPet pet = Pets.getInstance().getPPetManager().getPlayerPet(player.getUniqueId());
        if(pet == null) return;
        pet.destroy();

    }

}
