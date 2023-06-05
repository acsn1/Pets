package org.acsn1.pets.events;

import org.acsn1.glibrary.items.ItemBuilder;
import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();

        if(event.getRightClicked() instanceof Entity) {
            Entity en = event.getRightClicked();
            if(en == null) return;
            if(en.getCustomName() == null) return;
            for(PlayerPet ppets : Pets.getInstance().getPPetManager().getPlayerPets()) {

                    if (en.getCustomName().equalsIgnoreCase(ppets.getPetName())) {
                        Player a = Bukkit.getPlayer(ppets.getOwner());
                        if (a != null) {
                            player.sendMessage(ChatUtils.translateColor("&6This pet &8[&eLvl: &f" + (int) ppets.getLevel() + "&8] &6belongs to " + a.getName() + "!"));
                            player.sendMessage(ChatUtils.translateColor("&7Purchase a pet for yourself at &f&nhttps://store.example.com/&r&7!"));
                        }
                    }

            }

            event.setCancelled(true);
        }


    }

    @EventHandler
    public void onSpawnPet(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        for(Pet pet : Pets.getInstance().getPetManager().getPets()) {
            ItemStack hand = player.getInventory().getItemInHand();
            if(hand.getType().equals(pet.getMaterial())) {
                ItemMeta meta = hand.getItemMeta();
                if(meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase(ChatUtils.translateColor(pet.getItemName()))) {
                    player.getInventory().remove(hand);

                    new PlayerPet(player.getUniqueId(),
                            player.getName() + "'s Pet" ,pet, 1f).summon(player);

                    event.setCancelled(true);
                }
            }
        }
    }

}
