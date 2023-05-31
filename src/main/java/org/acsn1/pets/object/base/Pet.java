package org.acsn1.pets.object.base;

import org.acsn1.pets.Pets;
import org.bukkit.Material;

import java.util.List;

public class Pet {

    private String name;
    private BoostType boostType;
    private Material material;
    private String itemName;
    private List<String> lore;
    private String headValue;


    public Pet(String name, BoostType boostType, Material material, String itemName, List<String> lore, String headValue) {
        this.name = name;
        this.boostType = boostType;
        this.material = material;
        this.itemName = itemName;
        this.lore = lore;
        this.headValue = headValue;
    }

    public String getName() {
        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoostType getBoostType() {
        return boostType;
    }

    public void setBoostType(BoostType boostType) {
        this.boostType = boostType;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getHeadValue() {
        return headValue;
    }

    public void setHeadValue(String headValue) {
        this.headValue = headValue;
    }

    public void delete() {
        Pets.getInstance().getPetManager().deletePet(name);
    }
}
