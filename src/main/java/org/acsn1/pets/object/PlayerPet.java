package org.acsn1.pets.object;

import org.acsn1.pets.object.base.Pet;

import java.util.UUID;

public class PlayerPet {

    private final UUID owner;
    private String petName;
    private Pet pet;
    private float level;

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
}
