package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3.FragmentNews;

public class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
    public Context ctx;
    public String url;
    public String news;

    public HttpAsyncTask(Context ctx, String url){
        this.ctx = ctx;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        news = HttpParser.getNews(url);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if(FragmentNews.mInstance != null)
            FragmentNews.mInstance.loadNews(news);
    }
}

