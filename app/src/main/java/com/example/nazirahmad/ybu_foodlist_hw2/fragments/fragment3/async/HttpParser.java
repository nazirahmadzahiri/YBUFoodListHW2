package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3.async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpParser {
    public static String getNews(String urlString) {
        String news = "";

        Document document = null;

        try { //Get Document
            document = Jsoup.connect(urlString).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Element newsElement = document.select("div[class=contentNews]").get(0);

        news = newsElement.html();

        return news;
    }
}

