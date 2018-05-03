package com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2;

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
import com.example.nazirahmad.ybu_foodlist_hw2.fragments.fragment2.async.HttpAsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;

public class FragmentAnnouncement extends Fragment{
    public static FragmentAnnouncement mInstance = null;

    @BindView(R.id.progressBar)ProgressBar progressBar;
    @BindView(R.id.webView)WebView webView;

    //public View rootView;
    public ListView announce;
    public ArrayList<String> announcement;
    public ArrayList<String> links;


    public FragmentAnnouncement() {}
    public static FragmentAnnouncement newInstance() {
        mInstance = new FragmentAnnouncement();
        return mInstance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab2_announcement, container, false);
       // ButterKnife.bind(this, view);
        announce =(ListView) view.findViewById(R.id.announceList);

        new DescriptionAn().execute();




        //getAnnouncements();
        return view;


    }

    public void getAnnouncements(){
        new HttpAsyncTask(getActivity(), "http://ybu.edu.tr/muhendislik/bilgisayar/").execute();
    }

    public void loadAnnouncements(String announcements){
        webView.loadData(announcements, "text/html; charset=utf-8", "utf-8");
        webView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    // Description AsyncTask
    private class DescriptionAn extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                announcement= new ArrayList<String>();
                links= new ArrayList<String>();
                Document document = Jsoup.connect("http://www.ybu.edu.tr/muhendislik/bilgisayar/").get();
                Element masthead = document.select("div.caContent").first();
                Iterator<Element> ite = masthead.select("div.cncItem").iterator();
                //ite.next();

                while(ite.hasNext()){
                    Element div =ite.next();
                    announcement.add(div.text());
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
            ArrayAdapter a=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,announcement);
            announce.setAdapter(a);
            announce.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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