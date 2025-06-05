package com.example.pyeonyook_fe.api;

public class Notice {
    private int id;
    private int notice_type;
    private String title;
    private String author;
    private String url;
    private String publishedAt;

    public Notice() {}

    public Notice(int id, int type, String title, String author, String publishedAt) {
        this.id = id;
        this.notice_type = type;
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    //get,set
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNotice_type() { return notice_type; }
    public void setNotice_type(int notice_type) { this.notice_type = notice_type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getUrl() {return url;}
    public void setUrl(String url){this.url = url;}

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
}
