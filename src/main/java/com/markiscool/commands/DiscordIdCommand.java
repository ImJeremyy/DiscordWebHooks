package com.markiscool.commands;

import com.markiscool.Util;
import com.markiscool.configs.IdsConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *  /discordid Jeremy#7970
 */
public class DiscordIdCommand implements CommandExecutor {

    private IdsConfig idsCfg;

    public DiscordIdCommand(IdsConfig idsCfg) {
        this.idsCfg = idsCfg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            Util.send(sender, "&cYou are not a player!");
            return true;
        }
        if(args.length != 1) {
            Util.send(sender, "&cInvalid command usage. &7Usage: /discordid <discord_id>. Example: /discordid Mark#9030");
            return true;
        }

        if(!sender.hasPermission("discordwebhook.discordid")) {
            Util.send(sender, "&cYou do not have permission to this command. &7(discordwebhook.discordid)");
            return true;
        }

        Player player = (Player) sender;
        String discordId = args[0];
        boolean successful = idsCfg.register(player, discordId);

        if(successful) {
            Util.send(player, "&aDiscord ID saved successfully");
        } else {
            Util.send(player, "&cSomething went wrong when trying to save your Discord Id");
        }
        return true;
    }
}
