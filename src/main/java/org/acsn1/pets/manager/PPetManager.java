package org.acsn1.pets.manager;

import org.acsn1.pets.Pets;
import org.acsn1.pets.object.PlayerPet;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PPetManager {

    private File file;
    private YamlConfiguration config;

    public PPetManager() {
        file = new File(Pets.getInstance().getDataFolder() + "/pdata");

    }

    public void savePet(PlayerPet playerPet) {

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

}
