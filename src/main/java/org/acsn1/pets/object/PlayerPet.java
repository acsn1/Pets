package org.acsn1.pets.object;

import org.acsn1.glibrary.items.ItemBuilder;
import org.acsn1.pets.Pets;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerPet {

    private final UUID owner;
    private String petName;
    private Pet pet;
    private float level;
    private Entity entity;

    public PlayerPet(UUID owner, String petName, Pet pet, float level) {
        this.owner = owner;
        this.petName = petName;
        this.pet = pet;
        this.level = level;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void summon(Player player) {

        Pets.getInstance().getPPetManager().getLocSaves().putIfAbsent(player.getUniqueId(), null);
        player.sendMessage(ChatUtils.translateColor("&7You have summoned your &e" + pet.getName() + " &7pet!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

        ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(1.0,0,0.7), EntityType.ARMOR_STAND);
        stand.setVisible(false);
        stand.setGravity(false);
        //stand.setInvulnerable(true);
        // stand.setGliding(false);
        stand.setHelmet(new ItemBuilder(Material.PLAYER_HEAD, 1)
                .setName(ChatUtils.translateColor(petName)).build());

        stand.setCustomName(ChatUtils.translateColor(petName));
        stand.setCustomNameVisible(true);
        this.setEntity(stand);


        Pets.getInstance().getPPetManager().getPlayerPets().add(this);
    }

    public void save() {
        Pets.getInstance().getPPetManager().savePet(this);
    }

    public void destroy() {
        getEntity().remove();
        Pets.getInstance().getPPetManager().getPlayerPets().remove(this);
    }
}
