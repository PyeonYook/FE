package com.example.pyeonyook_fe.api;

public class AppSession {
    private static String idToken;
    public static String getIdToken() { return idToken; }
    public static void setIdToken(String token) { idToken = token; }
}
