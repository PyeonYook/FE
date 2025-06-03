package com.example.pyeonyook_fe.api;

public class Notice {
    private int id;
    private String type;   // "학사" 등
    private String title;
    private String author;
    private String publishedAt;

    public Notice() {}

    public Notice(int id, String type, String title, String author, String publishedAt) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    // getter/setter 모두 추가할 것!
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
}
