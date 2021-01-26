package com.markiscool.handlers;

import com.markiscool.DiscordWebHookPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import sun.net.www.http.HttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebHookHandler {

    private DiscordWebHookPlugin plugin;

    public WebHookHandler(DiscordWebHookPlugin plugin) {
        this.plugin = plugin;
    }

    public void post(String urlStr, String jsonStr) throws IOException {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            try {
                URL url = new URL(urlStr);

                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST"); // PUT is another valid option
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                int length = jsonStr.getBytes().length;
                http.setFixedLengthStreamingMode(length);
                http.connect();
                try(OutputStream os = http.getOutputStream()) {
                    os.write(jsonStr.getBytes());
                }
                http.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("URL STR: " + urlStr);
            System.out.println("JSON STR: " + jsonStr);

        });

    }

}
