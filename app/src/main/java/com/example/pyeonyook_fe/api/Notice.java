package com.example.pyeonyook_fe.api;

public class Notice {
    private int id;
    private int noticeType;
    private String title;
    private String author;
    private String url;
    private String publishedAt;

    public Notice(int id, int type, String title, String author, String publishedAt) {
        this.id = id;
        this.noticeType = type;
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    //get,set
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNoticeType() { return noticeType; }
    public void setNoticeType(int noticeType) { this.noticeType = noticeType; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getUrl() {return url;}
    public void setUrl(String url){this.url = url;}

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }
}
