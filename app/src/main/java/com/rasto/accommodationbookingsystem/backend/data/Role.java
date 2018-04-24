package com.rasto.accommodationbookingsystem.backend.data;

public class Role {
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static String[] getAllRoles() {
        return new String[] { USER, ADMIN };
    }
}
