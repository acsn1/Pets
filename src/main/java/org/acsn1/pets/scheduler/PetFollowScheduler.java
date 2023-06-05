package org.acsn1.pets.scheduler;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PetFollowScheduler {

    public void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Pets.getInstance(), () -> {

            for(PlayerPet pet : Pets.getInstance().getPPetManager().getPlayerPets()) {
                if (Pets.getInstance().getPPetManager().getLocSaves().containsKey(pet.getOwner())) {
                    Location loc = Pets.getInstance().getPPetManager().getLocSaves().get(pet.getOwner());

                    if(loc != null) {
                        pet.getEntity().teleport(loc);
                    }
                }
            }

        },0,10L);
    }

}
