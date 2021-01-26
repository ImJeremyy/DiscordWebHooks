package com.markiscool;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    public static void send(CommandSender toSend, String msg) {
        toSend.sendMessage(colourize(msg));
    }

    public static String colourize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
