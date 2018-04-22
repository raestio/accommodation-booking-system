package com.rasto.accommodationbookingsystem.repository;

public class Role {
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static String[] getAllRoles() {
        return new String[] { USER, ADMIN };
    }
}
