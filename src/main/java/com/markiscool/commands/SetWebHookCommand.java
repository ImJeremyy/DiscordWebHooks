package com.markiscool.commands;

import com.markiscool.Util;
import com.markiscool.configs.SettingsConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetWebHookCommand implements CommandExecutor {

    private SettingsConfig settingscfg;

    public SetWebHookCommand(SettingsConfig settingscfg) {
        this.settingscfg = settingscfg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!sender.hasPermission("discordwebhook.admin")) {
        /*if(!sender.isOp()) {*/
            Util.send(sender, "&cYou do not have permission to this command. &7(discordwebhook.admin)");
            return true;
        }

        if(args.length != 1) {
            Util.send(sender, "&cInvalid command usage. &7Usage: /setwebhook <url>");
            return true;
        }

        String url = args[0];
        settingscfg.setUrl(url);
        Util.send(sender, "&aSuccessfully set webhook URL.");
        return true;
    }
}
