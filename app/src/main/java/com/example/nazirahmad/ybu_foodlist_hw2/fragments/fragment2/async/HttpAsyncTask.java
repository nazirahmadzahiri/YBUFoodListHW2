package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2.FragmentAnnouncement;

public class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
    public Context ctx;
    public String url;
    public String announcements;

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

        announcements = HttpParser.getAnnouncements(url);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if(FragmentAnnouncement.mInstance != null)
            FragmentAnnouncement.mInstance.loadAnnouncements(announcements);
    }
}
