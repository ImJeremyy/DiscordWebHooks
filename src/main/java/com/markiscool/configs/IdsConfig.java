package com.markiscool.configs;

import com.markiscool.DiscordWebHookPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class IdsConfig {

    private DiscordWebHookPlugin plugin;

    private File fconf;
    private FileConfiguration config;

    public IdsConfig(DiscordWebHookPlugin plugin) {
        this.plugin = plugin;
        init();
    }

    public boolean register(OfflinePlayer player, String discordId) {
        UUID uuid = player.getUniqueId();
        String username = player.getName();
        config.set("users." + uuid.toString() + ".uuid", uuid.toString());
        config.set("users." + uuid.toString() + ".username", username);
        config.set("users." + uuid.toString() + ".discordid", discordId);
        try {
            saveConfig();
        } catch (IOException e) {
            System.out.println(player.getName() + " ran /discordid " + discordId + " and could not save into ids.yml");
            System.out.println("Data: " + uuid.toString() + " " + username + " " + discordId);
            System.out.println(e);
            return false;
        }
        return true;
    }

    public String[] getRegister(String input) {

        int length = input.length();

        String[] register = new String[3];

        if (length >= 3 && length <= 16) { // look for username
            String username = input;
            for(String u : config.getConfigurationSection("users").getKeys(false)) {
                String usernameStr = config.getString("users." + u + ".username");
                if(usernameStr.equalsIgnoreCase(username)) {
                    String discordId = config.getString("users." + u + ".discordid");
                    register[0] = u;            // [0] == uuid
                    register[1] = usernameStr; // [1] == username
                    register[2] = discordId;    // [2] == discordid
                    break;
                }
            }
        } else if(length >= 16) { // look for uuid
            for(String u : config.getConfigurationSection("users").getKeys(false)) {
                String uuidStr = config.getString("users." + u + ".uuid");
                if(uuidStr.equalsIgnoreCase(input)) {
                    String discordId = config.getString("users." + u + ".discordid");
                    register[0] = u;            // [0] == uuid
                    register[1] = config.getString("users." + u + ".username"); // [1] == username
                    register[2] = discordId;    // [2] == discordid
                    break;
                }
            }
        }
        return register;
    }

    private void init() {
        fconf = new File(plugin.getDataFolder(), "ids.yml");
        config = YamlConfiguration.loadConfiguration(fconf);

        if(!config.contains("users")) {
            config.createSection("users");
            try {
                saveConfig();
            } catch (IOException e) {
                System.out.println("Could not save ids.yml when initializing \'users\' section");
            }
        }
    }

    private void saveConfig() throws IOException {
        config.save(fconf);
    }

    /**
     * Unsafe because errors could arise and we won't know where it happened
     */
    public void unsafeSaveConfig() {
        try {
            saveConfig();
        } catch (IOException e) {
            System.out.println("Could not save config for some reason (unsafe function called)");
        }
    }

}
