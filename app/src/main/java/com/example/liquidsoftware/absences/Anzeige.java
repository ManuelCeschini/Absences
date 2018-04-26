package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Anzeige extends AppCompatActivity {

    int id;
    TextView titel;
    TextView datumanfang;
    TextView datumende;
    TextView begruendung;
    TextView textTitle;
    TextView textDatumAnfang;
    TextView textDatumEnde;
    TextView textBegruendung;
    Button deleteAnzeige;

    SwipeRefreshLayout swipeRefreshLayout;
    SimpleDateFormat sdf;
    Absence ab;
    AbsencesClient ac;
    ProgressBar ladekreis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_anzeige);
        deleteAnzeige = findViewById(R.id.deleteAnzeige);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchAbsenzen();
            }
        });
        ab = new Absence();
        ac = new AbsencesClient();
        sdf = new SimpleDateFormat();
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
        }, "absenz", id);
    }
    public void assignParams() {
        titel = findViewById(R.id.AnzeigeTitel);
        datumanfang = findViewById(R.id.AnzeigeDatumStart);
        datumende = findViewById(R.id.AnzeigeDatumEnde);
        begruendung = findViewById(R.id.AnzeigeBegruendung);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textDatumAnfang = (TextView) findViewById(R.id.textDatumAnfang);
        textDatumEnde = (TextView) findViewById(R.id.textDatumEnde);
        textBegruendung = (TextView) findViewById(R.id.textBegruendung);

        try {
            titel.setText(ab.getTitel());
            sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Toast.makeText(this, sdf.format(ab.getDatum_beginn()), Toast.LENGTH_SHORT).show();
            datumanfang.setText(sdf.format(ab.getDatum_beginn()));
            datumende.setText(sdf.format(ab.getDatum_ende()));
            begruendung.setText(ab.getGrund());

            textTitle.setText("Titel:");
            textDatumAnfang.setText("Von:");
            textDatumEnde.setText("Bis: ");
            textBegruendung.setText("Begründung:");

            deleteAnzeige.setVisibility(View.VISIBLE);
            //TODO Jürgen: Delete Funktion zum laufen bringen


        }catch (Exception e){
            System.out.println("Failed to load params in Anzeige: " + e);
        }
    }
}
