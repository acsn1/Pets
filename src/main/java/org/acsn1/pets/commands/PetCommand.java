package org.acsn1.pets.commands;

import org.acsn1.pets.object.base.UCommand;
import org.acsn1.pets.utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class PetCommand implements UCommand {

    @Override
    public String getName() {
        return "pet";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //
        if(args.length == 0) {
            sender.sendMessage(ChatUtils.translateColor("&cCommand is disabled."));
        }//
    }
}
