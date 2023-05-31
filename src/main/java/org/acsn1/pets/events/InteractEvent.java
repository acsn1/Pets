package org.acsn1.pets.events;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEvent implements Listener {

    @EventHandler
    public void onEggApply(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;

        for(Pet pet : Pets.getInstance().getPetManager().getPets()) {
            ItemStack hand = player.getInventory().getItemInMainHand();
            if(hand.getType().equals(pet.getMaterial())) {
                ItemMeta meta = hand.getItemMeta();
                if(meta.hasDisplayName() && meta.getDisplayName().equalsIgnoreCase(ChatUtils.translateColor(pet.getItemName()))) {

                }
            }
        }
    }

}
