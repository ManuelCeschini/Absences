package com.example.liquidsoftware.absences;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{

    ProgressBar ladekreis;
    SwipeRefreshLayout swipeRefreshLayout;
    ListView lv;
    AbsencesClient ac;
    Adapter adapter;
    private Schueler schueler;
    private boolean logedin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ladekreis = findViewById(R.id.ladekreis_main);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_main);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAbsenzen();
            }
        });
        schueler = new Schueler();
        //while (logedin == false){
            login();
        //}
        lv = findViewById(R.id.listView1);
        ac = new AbsencesClient();
        ArrayList<Absence> arr = new ArrayList<>();
        adapter = new Adapter(this, arr);
        try {
            if (adapter.getCount() == 0) {
                fetchAbsenzen();
            }
        }catch (Exception e){
            System.out.println("Fail to load params fron fetchAbsenzen");
        }
        lv.setAdapter(adapter);
        anzeige();
        actionButton();
    }

    public void anzeige(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Anzeige");
                //intent.putExtra("id", lv.getAdapter().getItem(position).toString());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    public void login(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Login");
                startActivity(intent);
            }
        });
    }


    public void actionButton(){
        FloatingActionButton abt = findViewById(R.id.actionButton);
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".AddEntry");
                startActivity(intent);
            }
        });
    }

    public void fetchAbsenzen() {
        ac.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray arr = null;
                ArrayList<Absence> absences;
                if (response != null) {
                    try {
                        arr = response.getJSONArray("absenz");
                        absences = Absence.fromJSON(arr);
                        adapter.clear();
                        adapter.addAll(absences);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ladekreis.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(MainActivity.super.getApplicationContext(), "Laden der Daten fehlgeschlagen", Toast.LENGTH_SHORT).show();
                ladekreis.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, "absenz",0);
    }
}

