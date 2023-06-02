package org.acsn1.pets.manager;

import org.acsn1.pets.Pets;
import org.acsn1.pets.commands.PetCommand;
import org.acsn1.pets.object.base.UCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class CommandManager implements CommandExecutor {

    private Set<UCommand> ucommands = new HashSet<>();

    public CommandManager() {

        // Add commands here and load them
        ucommands.add(new PetCommand());

        for(UCommand cmd : ucommands) {
            Pets.getInstance().getCommand(cmd.getName()).setExecutor(this);

        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        for(UCommand ucmd : ucommands) {
            if(cmd.getName().equalsIgnoreCase(ucmd.getName())) {

                ucmd.execute(sender, args);
            }
        }


        return false;
    }

}
