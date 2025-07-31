package dev.Fjc.hexSigns.configuration;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public interface FileInterface {
    void createConfig();
    void initializeConfiguration() throws IOException, InvalidConfigurationException;
}
