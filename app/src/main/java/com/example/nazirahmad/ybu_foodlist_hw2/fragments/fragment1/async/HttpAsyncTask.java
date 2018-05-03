package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1.FragmentFood;

public class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
    public Context ctx;
    public String url;
    public String foodList;

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

        foodList = HttpParser.getFoodList(url);

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if(FragmentFood.mInstance != null)
            FragmentFood.mInstance.loadFoodList(foodList);
    }
}

