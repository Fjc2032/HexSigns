package dev.Fjc.hexSigns.command;

import dev.Fjc.hexSigns.HexSigns;
import dev.Fjc.hexSigns.util.Perms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Reload implements CommandExecutor {

    @NotNull
    private final Plugin plugin = JavaPlugin.getPlugin(HexSigns.class);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission(Perms.ADMIN.getPermission())) {
            commandSender.sendMessage("No permission.");
        }
        if (command.getName().equals("hexsigns-reload")) {


            plugin.reloadConfig();
            commandSender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");


        }
        return true;
    }
}
