package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.nazirahmad.ybu_foodlist_hw2.R;
import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment3.async.HttpAsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;

public class FragmentNews extends Fragment{
    public static FragmentNews mInstance = null;

    @BindView(R.id.progressBar)ProgressBar progressBar;
    @BindView(R.id.webView)WebView webView;

    //public View rootView;
    public ListView newsList;
    public ArrayList<String> news;
    public ArrayList<String> links;


    public FragmentNews() {}
    public static FragmentNews newInstance() {
        mInstance = new FragmentNews();
        return mInstance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab3_news, container, false);
        newsList =(ListView) view.findViewById(R.id.newsListView);
        new DescriptionNe().execute();
        return view;

        //ButterKnife.bind(this, rootView);

        //getNews();


    }

    public void getNews(){
        new HttpAsyncTask(getActivity(), "http://ybu.edu.tr/muhendislik/bilgisayar/").execute();
    }

    public void loadNews(String news){
        webView.loadData(news, "text/html; charset=utf-8", "utf-8");
        webView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    // Description AsyncTask
    private class DescriptionNe extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                news= new ArrayList<String>();
                links= new ArrayList<String>();
                Document document = Jsoup.connect("http://www.ybu.edu.tr/muhendislik/bilgisayar/").get();
                Element masthead = document.select("div.cnContent").first();
                Iterator<Element> ite = masthead.select("div.cncItem").iterator();
                //ite.next();

                while(ite.hasNext()){
                    Element div =ite.next();
                    news.add(div.text());
                    System.out.println("Value 1: " + div.select("a").attr("href"));
                    links.add(div.select("a").attr("href"));



                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            //  TextView txtdesc = (TextView) findViewById(R.id.desctxt);
            ArrayAdapter a=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,news);
            newsList.setAdapter(a);
            newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {


                    String[] a = new String[10000];
                    for(int i=0;i<links.size();i++){
                        a[i]="http://www.ybu.edu.tr/muhendislik/bilgisayar/"+links.get(i).toString();
                    }

                    Uri uri = Uri.parse(a[position]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);






                }
            });

            // foodList.setText(a);
            //mProgressDialog.dismiss();
        }
    }




}


