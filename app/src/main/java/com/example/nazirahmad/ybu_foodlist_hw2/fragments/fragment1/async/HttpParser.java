package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1.async;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpParser {
    public static String getFoodList(String urlString) {
        String foodList = "";

        Document document = null;

        try { //Get Document
            document = Jsoup.connect(urlString).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Element foodListElement = document.select("table").get(0);

        foodList = foodListElement.html();

        return foodList;
    }
}

