package dev.Fjc.hexSigns.util;

import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public enum Perms {

    ADMIN("fjc.hexsigns.admin", PermissionDefault.OP),
    EDIT_SIGN("fjc.hexsigns.edit.sign", PermissionDefault.TRUE),
    EDIT_TEXT("fjc.hexsigns.edit.text", PermissionDefault.TRUE);

    private final String permission;
    private final PermissionDefault def;

    Perms(String permission, @NotNull PermissionDefault def) {
        this.permission = permission;
        this.def = def;
    }

    public String getPermission() {
        return this.permission;
    }

    public PermissionDefault getDefault() {
        return this.def;
    }
}
