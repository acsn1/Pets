package org.acsn1.pets.manager;

import org.acsn1.glibrary.items.ItemBuilder;
import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.acsn1.pets.object.base.BoostType;
import org.acsn1.pets.object.base.Pet;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.util.*;

public class PPetManager {

    private File file;
    private YamlConfiguration config;
    private Map<UUID, Location> temp_loc_saves = new HashMap<>();
    private Set<PlayerPet> ppets = new HashSet<>();

    public PPetManager() {
        file = new File(Pets.getInstance().getDataFolder() + "/pdata");

    }

    public void savePet(PlayerPet playerPet) {
        temp_loc_saves.put(playerPet.getOwner(), null);
        file = new File(Pets.getInstance().getDataFolder() + "/pdata", playerPet.getOwner().toString() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        //save-data
        config.set("owner", playerPet.getOwner().toString());
        config.set("personal_name", playerPet.getPetName());
        config.set("level", playerPet.getLevel());
        config.set("name", playerPet.getPet().getName());

        try{
            config.save(file);
        } catch(Exception ex) {}

        ppets.add(playerPet);
    }

    public boolean hasPet(UUID uuid) {
        file = new File(Pets.getInstance().getDataFolder() + "/pdata", uuid.toString() + ".yml");
        if(file.exists()) return true;
        return false;

    }

    public PlayerPet getPlayerPet(UUID uuid) {
        for(PlayerPet pets : ppets) {
            if(pets.getOwner().equals(uuid)) {
                return pets;
            }
        }
        return null;
    }

    public PlayerPet getPetFromFile(UUID uuid) {

        file = new File(Pets.getInstance().getDataFolder() + "/pdata", uuid.toString() + ".yml");
        config = YamlConfiguration.loadConfiguration(file);

        String name = config.getString("personal_name");
        float level = (float) config.getDouble("level");

        String type = config.getString("name");
        Pet pet = Pets.getInstance().getPetManager().getPet(type);
        if(pet == null) return null;

        return new PlayerPet(uuid, name, pet, level);

    }


    public Set<PlayerPet> getPlayerPets() { return ppets; }
    public Map<UUID, Location> getLocSaves() { return this.temp_loc_saves; }

}
