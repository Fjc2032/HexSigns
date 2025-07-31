package dev.Fjc.hexSigns.listener;

import dev.Fjc.hexSigns.HexSigns;
import dev.Fjc.hexSigns.util.Perms;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignListener implements Listener {

    @NotNull
    private final Plugin plugin = JavaPlugin.getPlugin(HexSigns.class);

    @NotNull
    private final FileConfiguration config = plugin.getConfig();

    @NotNull
    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private final boolean isSignEnabled = config.getBoolean("Sign.isEnabled", true);
    private final boolean isTextEnabled = config.getBoolean("Text.isEnabled", true);

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onSignAlter(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (!isSignEnabled || !hasPermission(player)) return;
        String[] lines = event.getLines();
        int lineSet = lines.length;
        for (int i = 0; i < lineSet; i++) {
            String original = lines[i];
            String hexLine = buildHexString(original);
            event.setLine(i, hexLine);
        }

    }
    //Might as well add support for the chat too.
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onSentMessage(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!isTextEnabled || !hasPermission(player)) return;
        event.setMessage(buildHexString(event.getMessage()));
    }

    /**
     * Applies hex code/legacy coloring to the provided string, if applicable.
     * @param text The text that should be modified.
     * @return The new text, as a string.
     */
    @NotNull
    private String buildHexString(String text) {
        Matcher match = pattern.matcher(text);
        while (match.find()) {
            String parsedMessage = text.substring(match.start(), match.end());
            text = text.replace(parsedMessage, ChatColor.of(parsedMessage) + "");
            match = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private boolean hasPermission(@NotNull Player player) {
        String permission = config.getString("Permission", "fjc.hexsigns.edit");
        return player.hasPermission(permission);
    }
}
