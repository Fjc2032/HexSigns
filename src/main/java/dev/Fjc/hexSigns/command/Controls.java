package dev.Fjc.hexSigns.command;

import dev.Fjc.hexSigns.HexSigns;
import dev.Fjc.hexSigns.util.Perms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controls implements TabExecutor {

    @NotNull
    private final Plugin plugin = JavaPlugin.getPlugin(HexSigns.class);

    @NotNull
    private final FileConfiguration config = plugin.getConfig();

    @NotNull
    private final List<String> arguments = List.of(
            "sign",
            "text"
    );
    @NotNull
    private final List<String> actions = List.of(
            "enable",
            "disable"
    );

    private final String syntax = ChatColor.YELLOW + "Syntax: /hexsigns <sign/text> <enable/disable>";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("hexsigns")) {

            //Debug
            plugin.getLogger().info("[HEX-SIGNS] [DEBUG] Command executed with arguments: " + Arrays.toString(args));

            if (args.length != 2) {
                commandSender.sendMessage(ChatColor.RED + "Missing or incorrect arguments.");
                commandSender.sendMessage(syntax);
                return true;
            }

            @NotNull final String selection = args[0].toLowerCase();
            @NotNull final String action = args[1].toLowerCase();

            if (!commandSender.hasPermission(Perms.ADMIN.getPermission())) {
                commandSender.sendMessage(ChatColor.RED + "No permission.");
            }

            if (!arguments.contains(selection)) {
                commandSender.sendMessage(ChatColor.RED + "Unknown argument.");
                commandSender.sendMessage(syntax);
            }
            if (!actions.contains(action)) {
                commandSender.sendMessage(ChatColor.RED + "Unknown argument.");
                commandSender.sendMessage(syntax);
            }
            switch (selection) {
                case "sign" -> config.set("Text.isEnabled", action.equals("enable"));
                case "text" -> config.set("Sign.isEnabled", action.equals("enable"));
            }
            plugin.saveConfig();
            commandSender.sendMessage(ChatColor.GREEN + "Success.");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        @NotNull List<String> preview = new ArrayList<>(this.arguments);
        if (!commandSender.hasPermission(Perms.ADMIN.getPermission())) return List.of();
        if (command.getName().equalsIgnoreCase("hexsigns")) {
            if (args.length == 1) {
                preview.addAll(this.arguments);
                preview.removeAll(this.actions);
                return List.copyOf(preview);
            }
            if (args.length == 2) {
                preview.addAll(this.actions);
                preview.removeAll(this.arguments);
                return List.copyOf(preview);
            }
        }
        return List.copyOf(preview);
    }
}
