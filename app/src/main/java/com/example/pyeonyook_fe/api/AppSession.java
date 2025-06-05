package com.example.pyeonyook_fe.api;

public class AppSession {
    private static String idToken;
    public static void setIdToken(String token) { idToken = token; }
    public static String getIdToken() { return idToken; }
}
