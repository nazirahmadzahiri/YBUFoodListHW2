package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.nazirahmad.ybu_foodlist_hw2.R;
import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment1.async.HttpAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFood extends Fragment{
    public static FragmentFood mInstance = null;

    @BindView(R.id.progressBar)ProgressBar progressBar;
    @BindView(R.id.webView)WebView webView;

    public View rootView;

    public FragmentFood() {}
    public static FragmentFood newInstance() {
        mInstance = new FragmentFood();
        return mInstance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, rootView);

        getFoodList();

        return rootView;
    }

    public void getFoodList(){
        new HttpAsyncTask(getActivity(), "http://ybu.edu.tr/sks/").execute();
    }

    public void loadFoodList(String foodList){
        webView.loadData(foodList, "text/html; charset=utf-8", "utf-8");
        webView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
