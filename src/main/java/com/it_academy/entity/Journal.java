package com.it_academy.entity;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private String title;
    private Contact contacts;
    private static List<Article> articles = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    public static void setArticles(List<Article> articles) {
        Journal.articles = articles;
    }

    @Override
    public String toString() {
        return "Journal{\n" +
                "title='" + title + '\'' + ",\n" +
                "contacts=" + contacts + ",\n" +
                "articles=" + articles + "\n" +
                '}';
    }
}
