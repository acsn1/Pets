package org.acsn1.pets.manager;

import org.acsn1.pets.Pets;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.HashSet;
import java.util.Set;

public class ListenerManager {


    private Set<Listener> listeners = new HashSet<>();
    public ListenerManager() {

        /*
         * Add all bukkit events into the hash-set
         */


        // Loop through all the events and register them
        PluginManager pm = Bukkit.getPluginManager();
        for(Listener listener : listeners) {
            pm.registerEvents(listener, Pets.getInstance());
        }

    }

}
