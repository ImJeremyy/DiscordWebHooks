package com.markiscool;

import com.markiscool.commands.DiscordIdCommand;
import com.markiscool.commands.SetWebHookCommand;
import com.markiscool.commands.WebHookCommand;
import com.markiscool.configs.IdsConfig;
import com.markiscool.configs.SettingsConfig;
import com.markiscool.handlers.WebHookHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordWebHookPlugin extends JavaPlugin {

    private SettingsConfig settingscfg;
    private IdsConfig idscfg;

    private WebHookHandler wh;

    @Override
    public void onEnable() {
        initConfigs();
        initHandlers();
        initCommands();
    }

    @Override
    public void onDisable() {
        settingscfg.saveConfig();
        idscfg.unsafeSaveConfig();
    }

    private void initConfigs() {
        settingscfg = new SettingsConfig(this);
        idscfg = new IdsConfig(this);
    }

    private void initHandlers() {
        wh = new WebHookHandler(this);
    }

    private void initCommands() {
        this.getCommand("discordid").setExecutor(new DiscordIdCommand(idscfg));
        this.getCommand("webhook").setExecutor(new WebHookCommand(idscfg, settingscfg, wh));
        this.getCommand("setwebhook").setExecutor(new SetWebHookCommand(settingscfg));
    }
}
