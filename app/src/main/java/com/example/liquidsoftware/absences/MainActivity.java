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
    private boolean loggedIn = false;
    private String emailString;
    private String passwordString;
    private int idInt;
    TextView numberAbsences;
    TextView hAbsences;
    private int numberAbsencesInt = 0;
    private int hAbsencesInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        idInt = intent.getIntExtra("id", 0);
        if (idInt == 0) {
            Toast.makeText(MainActivity.this.getApplicationContext(), "Du musst eingeloggt sein", Toast.LENGTH_SHORT).show();
            finish();
        }
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

    @Override
    public void onResume()
    {
        super.onResume();
        fetchAbsenzen();
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

        //TODO Jürgen: stunden und prozent felder füllen
    }

    public void anzeige(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Anzeige");
                Absence a = adapter.getItem(position);
                intent.putExtra("absenz_id", a.getId());
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
                intent.putExtra("schueler_id", idInt);
                intent.setClassName(getPackageName(), getPackageName() + ".AddEntry");
                startActivity(intent);
            }
        });
    }

    public void fetchAbsenzen() {
        adapter.clear();
        ladekreis.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(true);
        ac.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray arr = null;
                ArrayList<Absence> absences = new ArrayList<>();
                if (response != null) {

                    try {
                        arr = response.getJSONArray("absenz");
                        absences = Absence.fromJSON(arr);
                    } catch (JSONException e) {
                        absences.add(Absence.fromJSON(response));
                        e.printStackTrace();
                    } finally {
                        adapter.addAll(absences);
                        numberAbsencesInt = absences.size();
                        status();
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
        }, "absenzBySchueler", idInt);
    }

}

