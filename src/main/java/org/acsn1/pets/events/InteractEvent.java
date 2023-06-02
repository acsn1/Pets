package org.acsn1.pets.events;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

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
                    player.getInventory().remove(hand);

                    player.sendMessage(ChatUtils.translateColor("&7You have summoned your &e" + pet.getName() + " &7pet!"));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

                    // Saving pet into a .yml file in pdata folder
                    Pets.getInstance().getPPetManager().savePet(new PlayerPet(player.getUniqueId(),
                    player.getName() + "'s Pet" ,pet, 1f));

                    // Spawning the item as entity on the world with custom name
                    Entity en = player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.PLAYER_HEAD, 1));
                    en.setCustomName(ChatUtils.translateColor(player.getName() + "'s Pet"));
                    en.setCustomNameVisible(true);

                    // Item pickup delay set to max
                    Item i = (Item) en;
                    i.setPickupDelay(Integer.MAX_VALUE);

                    // TP/Follow test [Basically like a laggy player]
                    new BukkitRunnable() {
                        int k = 2;
                        public void run() {

                            List<Location> locs = Pets.getInstance().getPPetManager().getLocSaves().get(player.getUniqueId());
                            if(locs!= null) {
                                int max = locs.size();
                                if (locs.size() > 2) {
                                    if (en.getLocation() != locs.get(max - 1)) {
                                        if (max > k) {
                                            // start slowing teleporting to previous location, everytime k++
                                            en.teleport(locs.get(k-1));
                                            k++;
                                        } else{
                                            // not move
                                            en.setVelocity(player.getVelocity());
                                        }
                                    }

                                }
                            }
                        }
                    }.runTaskTimer(Pets.getInstance(), 0l, 5l);

                    event.setCancelled(true);
                }
            }
        }
    }

}
