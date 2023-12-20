package com.example.doanck.ModelLogin;

public class Username {
    private static String name;
    private static String email;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Username.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Username.email = email;
    }
}
