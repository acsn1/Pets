package org.acsn1.pets;

import org.acsn1.pets.manager.CommandManager;
import org.acsn1.pets.manager.ListenerManager;
import org.acsn1.pets.manager.PPetManager;
import org.acsn1.pets.manager.PetManager;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pets extends JavaPlugin {

    private static Pets instance;
    private PetManager petManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private PPetManager pPetManager;


    @Override
    public void onEnable() {

        instance = this;

        init();
    }

    @Override
    public void onDisable() {
        stop();
    }

    public static Pets getInstance() {
        return instance;
    }

    private void init() {
        loadManagers();
        Bukkit.getConsoleSender().sendMessage(ChatUtils.translateColor("&a[Pets] has finished loading!"));
    }

    private void stop() {
        for(World w : Bukkit.getWorlds()) {
            for(Entity en : w.getEntities()) {
                if(en.getCustomName() != null) {
                    if(en.getCustomName().contains("Pet")) {
                        en.remove();
                    }
                }
            }
        }
    }


    private void loadManagers() {
        petManager = new PetManager();
        listenerManager = new ListenerManager();
        commandManager = new CommandManager();
        petManager = new PetManager();
        pPetManager = new PPetManager();
    }

    public PetManager getPetManager() { return petManager; }
    public PPetManager getPPetManager() { return pPetManager; }

}
