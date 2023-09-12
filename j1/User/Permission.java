package com.example.j1.j1.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete");

    @Getter
    private String permission; // Use "permission" instead of "permissions"

    Permission(String permission) {
        this.permission = permission; // Update the field name here
    }
}