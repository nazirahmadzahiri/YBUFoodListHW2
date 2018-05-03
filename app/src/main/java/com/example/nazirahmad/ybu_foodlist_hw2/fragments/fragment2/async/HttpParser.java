package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2.async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpParser {
    public static String getAnnouncements(String urlString) {
        String announcements = "";

        Document document = null;

        try { //Get Document
            document = Jsoup.connect(urlString).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Element announcementsElement = document.select("div[class=contentAnnouncements]").get(0);

        announcements = announcementsElement.html();

        return announcements;
    }
}

