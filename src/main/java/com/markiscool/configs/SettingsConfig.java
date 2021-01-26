package com.markiscool.configs;

import com.markiscool.DiscordWebHookPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsConfig {

    private DiscordWebHookPlugin plugin;

    private File file;
    private FileConfiguration config;

    public SettingsConfig(DiscordWebHookPlugin plugin) {
        this.plugin = plugin;
        init();
    }

    public void setUrl(String url) {
        config.set("settings.url", url);
        saveConfig();
    }

    public String getUrl() {
        return config.getString("settings.url");
    }

    public void saveConfig() {
        try  {
            config.save(file);
        } catch (IOException e) {
            System.out.println("An error occurred saving settings.yml");
        }
    }

    private void init() {
        file = new File(plugin.getDataFolder(), "settings.yml");
        config = YamlConfiguration.loadConfiguration(file);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Could not create settings.yml for some reason");
        }

        if(!config.contains("settings")) {
            config.createSection("settings");
            config.set("settings.url", "");
            saveConfig();
        }
    }

}
