package com.example.pyeonyook_fe.api;

// 요청 VO
public class IdTokenRequest {
    private String idToken;

    public IdTokenRequest(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
