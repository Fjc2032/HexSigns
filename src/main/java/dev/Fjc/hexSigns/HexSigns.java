package dev.Fjc.hexSigns;

import dev.Fjc.hexSigns.command.Controls;
import dev.Fjc.hexSigns.command.Reload;
import dev.Fjc.hexSigns.configuration.FileBuilder;
import dev.Fjc.hexSigns.listener.SignListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class HexSigns extends JavaPlugin {

    Plugin plugin = this;

    private FileBuilder fileBuilder;

    @Override
    public void onEnable() {
        plugin.getServer().getPluginManager().registerEvents(new SignListener(), this);

        fileBuilder = new FileBuilder(plugin);
        fileBuilder.createConfig();
        fileBuilder.initializeConfiguration();

        this.executor(new Reload(), "hexsigns-reload");
        this.executor(new Controls(), "hexsigns");
        this.completer(new Controls(), "hexsigns");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    private void executor(@NotNull CommandExecutor executor, String commandName) {
        this.getCommand(commandName).setExecutor(executor);
    }
    private void completer(@NotNull TabCompleter completer, String commandName) {
        this.getCommand(commandName).setTabCompleter(completer);
    }

    public FileBuilder getFileBuilder() {
        return this.fileBuilder;
    }
}
