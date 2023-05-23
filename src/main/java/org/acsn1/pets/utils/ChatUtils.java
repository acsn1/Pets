package org.acsn1.pets.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String translateColor(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
