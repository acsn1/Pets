package org.acsn1.pets.manager;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.base.BoostType;
import org.acsn1.pets.object.base.Pet;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetManager {

    private File file;
    private YamlConfiguration config;
    private Set<Pet> pets = new HashSet<>();


    public PetManager() {

        file = new File(Pets.getInstance().getDataFolder(),"/pets");

        if(!(file.exists())) file.mkdirs();

        for(File files : file.listFiles()) {

            config = YamlConfiguration.loadConfiguration(files);
            String name = config.getString("name");
            BoostType boostType = BoostType.valueOf(config.getString("boost"));
            Material material = Material.matchMaterial(config.getString("material"));
            String itemName = config.getString("item-name");
            List<String> lore = config.getStringList("lore");
            String headValue = config.getString("head-value");

            pets.add(new Pet(name, boostType, material, itemName, lore, headValue));

        }
    }

    public void createPet(String name, BoostType boostType) {

        Pet pet = getPet(name);
        if(pet != null) return;

        List<String> lore = new ArrayList<>();
        lore.add("&6"+boostType.name() + " boost!");

        String itemName = "&a"+ name + " Pet";

        pet = new Pet(name, boostType, Material.GRASS, itemName
        , lore, "0");

        pets.add(pet);
        savePet(pet);
    }

    public void deletePet(String name) {

        Pet pet = getPet(name);
        if(pet == null) return;

        file = new File(Pets.getInstance().getDataFolder() + "/pets/", pet.getName() + ".yml");
        if(file.exists()) file.delete();
        pets.remove(pet);
    }

    public void savePet(Pet pet) {

        file = new File(Pets.getInstance().getDataFolder() + "/pets/", pet.getName() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        config.set("name", pet.getName());
        config.set("boost", pet.getBoostType().name());
        config.set("material", pet.getMaterial().name());
        config.set("item-name", pet.getItemName());
        config.set("lore", pet.getLore().toString());
        config.set("head-value", pet.getHeadValue());


        try{
            config.save(file);
        } catch(Exception ignored) {}
    }

    public Pet getPet(String name) {
        for(Pet pet : pets) {
            if(pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    public Set<Pet> getPets() {
        return pets;
    }


}
