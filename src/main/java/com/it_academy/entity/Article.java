package com.it_academy.entity;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private int id;
    private String title;
    private String author;
    private String url;
    private List<String> hotkeys = new ArrayList<>();

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHotkeys(List<String> hotkeys) {
        this.hotkeys = hotkeys;
    }

    @Override
    public String toString() {
        return "\nArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", hotkeys='" + hotkeys + '\'' +
                '}';
    }
}
