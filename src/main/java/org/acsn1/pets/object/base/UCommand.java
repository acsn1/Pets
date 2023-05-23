package org.acsn1.pets.object.base;

import org.bukkit.command.CommandSender;

public interface UCommand {

    String getName();
    String getPermission();
    void execute(CommandSender sender, String[] args);

}
