package dev.Fjc.hexSigns.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileBuilder implements FileInterface {
    @NotNull
    private final Plugin plugin;

    @NotNull
    private final FileConfiguration configuration;

    public FileBuilder(Plugin plugin) {
        this.plugin = plugin;
        this.configuration = plugin.getConfig();
    }

    @Override
    public void createConfig() {
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (file.exists()) return;
        file.getParentFile().mkdirs();

        plugin.saveResource("config.yml", false);
    }

    @Override
    public void initializeConfiguration() {
        configuration.addDefault("Sign.isEnabled", true);
        configuration.addDefault("Text.isEnabled", true);

        configuration.options().copyDefaults(true);

        plugin.saveConfig();
    }

}
