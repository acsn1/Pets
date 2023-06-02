package org.acsn1.pets.manager;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class PPetManager {

    private File file;
    private YamlConfiguration config;
    private Map<UUID, List<Location>> temp_loc_saves = new HashMap<>();
    public PPetManager() {
        file = new File(Pets.getInstance().getDataFolder() + "/pdata");

    }

    public void savePet(PlayerPet playerPet) {
        temp_loc_saves.put(playerPet.getOwner(), new ArrayList<>());
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
    }

    public boolean hasPet(UUID uuid) {
        file = new File(Pets.getInstance().getDataFolder() + "/pdata", uuid.toString() + ".yml");
        if(file.exists()) return true;
        return false;

    }

    public Map<UUID, List<Location>> getLocSaves() { return this.temp_loc_saves; }

}
