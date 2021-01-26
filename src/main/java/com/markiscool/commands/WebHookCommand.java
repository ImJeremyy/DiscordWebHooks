package com.markiscool.commands;

import com.markiscool.Util;
import com.markiscool.handlers.WebHookHandler;
import com.markiscool.configs.IdsConfig;
import com.markiscool.configs.SettingsConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONObject;

import java.io.IOException;

public class WebHookCommand implements CommandExecutor {

    private IdsConfig idscfg;
    private SettingsConfig settingscfg;
    private WebHookHandler wh;

    public WebHookCommand(IdsConfig idscfg, SettingsConfig settingscfg, WebHookHandler wh) {
        this.idscfg = idscfg;
        this.settingscfg = settingscfg;
        this.wh = wh;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(!sender.hasPermission("discordwebhook.admin")) {
        /*if(!sender.isOp()) {*/
            Util.send(sender, "&cYou do not have permission to this command! &7(discordwebhook.admin)");
            return true;
        }

        if(args.length != 1) {
            Util.send(sender, "&cInvalid commmand usage. &7Usage: /webhook <username/uuid> Example: /webhook ItzzGamma");
            return true;
        }

        String arg0 = args[0];
        if(arg0.length() < 3) {
            Util.send(sender, "&cArg0's length is less than 3 which is invalid. &7(Usernames <= 16 and UUIDS > 16");
        }

        String url = settingscfg.getUrl();
        if (url == "") {
            Util.send(sender, "&cURL invalid. &7/setwebhook <url>");
            return true;
        }
        String input = arg0;
        String[] register = idscfg.getRegister(input);
        if(register[0] != null) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("uuid", register[0]);
            jsonObj.put("username", register[1]);
            jsonObj.put("discordid", register[2]);

            String jsonString = jsonObj.toJSONString();
            try {
                wh.post(url, jsonString);
                Util.send(sender, "&aSuccessfully sent webhook with JSON: &e" + jsonString + " &ato URL: &e" + url);
            } catch (IOException e) {
                Util.send(sender, "&cError occurred while trying to POST to &e" + url);
                Util.send(sender, "&cInfo: &r" + jsonString);
            }
        } else {
            Util.send(sender, "&cCould not find entry with given input: &e" + input + " &7They must do: /discordid " + input + "#123123");
        }

        return true;
    }
}
