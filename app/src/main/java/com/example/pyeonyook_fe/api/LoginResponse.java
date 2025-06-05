package com.example.pyeonyook_fe.api;

// 응답 VO(필요 필드만 선언)
public class LoginResponse {
    private Long id;
    private String uid;
    private String email;
    private String name;

    // getter/setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
