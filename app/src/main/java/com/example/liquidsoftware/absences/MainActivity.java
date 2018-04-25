package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{

    ProgressBar ladekreis;
    SwipeRefreshLayout swipeRefreshLayout;
    ListView lv;
    AbsencesClient ac;
    Adapter adapter;
    private Schueler schueler;
    private boolean logedin = false;
    private String emailString;
    private String passwordString;
    TextView numberAbsences;
    TextView hAbsences;
    TextView percentAbsences;
    private int numberAbsencesInt = 0;
    private int hAbsencesInt = 0;
    private int percentAbsencesInt = 0;

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
        lv = findViewById(R.id.listView1);
        numberAbsences = findViewById(R.id.numberAbsences);
        hAbsences = findViewById(R.id.hAbsences);
        percentAbsences = findViewById(R.id.percentAbsences);
        ac = new AbsencesClient();
        ArrayList<Absence> arr = new ArrayList<>();
        adapter = new Adapter(this, arr);
        Intent intent = getIntent();
        emailString = intent.getStringExtra("email");
        passwordString = intent.getStringExtra("password");
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
    public void status(){

        if (numberAbsencesInt != 0){
            numberAbsences.setText(numberAbsencesInt + "A");
        }else{
            numberAbsences.setText("0");
        }
        if (hAbsencesInt != 0){

        }else{
            hAbsences.setText("0:00h");
        }
        if (percentAbsencesInt != 0){

        }else{
            percentAbsences.setText("0%");
        }

        //TODO Jürgen: stunden und prozent felder füllen
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
                        numberAbsencesInt = absences.size();
                        status();
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

