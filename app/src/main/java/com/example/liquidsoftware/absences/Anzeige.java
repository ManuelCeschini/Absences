package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Anzeige extends AppCompatActivity {

    int id;
    TextView titel;
    TextView datumanfang;
    TextView datumende;
    TextView begruendung;
    SwipeRefreshLayout swipeRefreshLayout;
    Absence ab;
    AbsencesClient ac;
    ProgressBar ladekreis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_anzeige);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAbsenzen();
            }
        });
        ab = new Absence();
        ac = new AbsencesClient();
        Intent ii = getIntent();
        Bundle bundle = ii.getExtras();
        ladekreis = findViewById(R.id.ladekreis_anzeige);
        if(bundle != null){
            id = bundle.getInt("position") + 1;
        }
        try {
            fetchAbsenzen();
        }catch (Exception e){
            System.out.println("Failed to load fetchAbsenzen in Anzeige: " + e);
        }

    }
    public void fetchAbsenzen(){
        ac.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    try {
                        ab = Absence.fromJSON(response);
                        assignParams();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ladekreis.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Anzeige.super.getApplicationContext(), "Laden der Daten fehlgeschlagen", Toast.LENGTH_SHORT).show();
                ladekreis.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, id);
    }
    public void assignParams() {
        titel = findViewById(R.id.AnzeigeTitel);
        datumanfang = findViewById(R.id.AnzeigeDatumStart);
        datumende = findViewById(R.id.AnzeigeDatumEnde);
        begruendung = findViewById(R.id.AnzeigeBegruendung);
        try {
            titel.setText("Titel:\n" + ab.getTitel());
            datumanfang.setText("Anfang:\n" +ab.getDatum_beginn());
            datumende.setText("Ende:\n" +ab.getDatum_ende());
            begruendung.setText("Begründung:\n" +ab.getGrund());
        }catch (Exception e){
            System.out.println("Failed to load params in Anzeige: " + e);
        }
    }
}
