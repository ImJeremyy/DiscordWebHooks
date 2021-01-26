# DiscordWebHooks
 Spigot 1.16.5 made for ItzzGamma

# Commands:
- /discordid <discord_id> [discordwebhook.discordid]
- /webhook <username/uuid> [discordwebhook.admin]
- /setwebhook <url> [discordwebhook.admin]

# Setup:
1. Set the Web Hook URL with: /setwebhook <url>. Can also manually setup in settings.yml
2. Have player do /discordid <discord_id> to save their data in ids.yml
3. Have admin/console do /webhook <username/uuid> (Works with both usernames & uuids) to send the JSON to the URL.

# Request Property 
Content-Type set to: "application/json; charset=UTF-8"
