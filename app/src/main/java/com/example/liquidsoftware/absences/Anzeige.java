package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

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
    RelativeLayout relativeLayout;
    SimpleDateFormat sdf;
    Absence ab;
    AbsencesClient ac;
    ProgressBar ladekreis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_anzeige);
        relativeLayout = findViewById(R.id.relative_layout_anzeige);
        titel = findViewById(R.id.AnzeigeTitel);
        datumanfang = findViewById(R.id.AnzeigeDatumStart);
        datumende = findViewById(R.id.AnzeigeDatumEnde);
        begruendung = findViewById(R.id.AnzeigeBegruendung);
        textTitle = findViewById(R.id.textTitle);
        textDatumAnfang = findViewById(R.id.textDatumAnfang);
        textDatumEnde = findViewById(R.id.textDatumEnde);
        textBegruendung = findViewById(R.id.textBegruendung);
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
        id = ii.getIntExtra("absenz_id", 0);
        ladekreis = findViewById(R.id.ladekreis_anzeige);
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
                relativeLayout.setVisibility(View.VISIBLE);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Anzeige.super.getApplicationContext(), "Laden der Daten fehlgeschlagen", Toast.LENGTH_SHORT).show();
                ladekreis.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, "absenz", id);
    }
    public void assignParams() {
        try {
            titel.setText(ab.getTitel());
            sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            datumanfang.setText(sdf.format(ab.getDatum_beginn()));
            datumende.setText(sdf.format(ab.getDatum_ende()));
            begruendung.setText(ab.getGrund());

            textTitle.setText("Titel:");
            textDatumAnfang.setText("Von:");
            textDatumEnde.setText("Bis: ");
            textBegruendung.setText("Begründung:");
            deleteEntry();
        } catch (Exception e){
            System.out.println("Failed to load params in Anzeige: " + e);
        }
    }

    public void deleteEntry() {
        deleteAnzeige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams rp = new RequestParams();
                rp.put("absenz_id", id);
                client.clearCredentialsProvider();
                client.post("http://absences.bplaced.net/Absences_Webservice/delete.php", rp, new TextHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString){
                        Toast.makeText(Anzeige.super.getApplicationContext(), "Löschen erfolgreich", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(Anzeige.super.getApplicationContext(), "Löschen fehlgeschlagen", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
/*
    public void exAnzeige(){
        setEntschuldigtBtn();
        exAnzeige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.GONE);
                ladekreis.setVisibility(View.VISIBLE);
                ab.setEntschuldigt(!ab.isEntschuldigt());
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams rp = new RequestParams();
                rp.put("absenz_id", ab.getId());
                rp.put("entschuldigt", ab.isEntschuldigt());
                client.clearCredentialsProvider();
                client.post("http://absences.bplaced.net/Absences_Webservice/changeAbsenceState.php", rp, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        relativeLayout.setVisibility(View.VISIBLE);
                        ladekreis.setVisibility(View.GONE);
                        Toast.makeText(Anzeige.this, "Eintrag konnte nicht verändert werden", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        setEntschuldigtBtn();
                        relativeLayout.setVisibility(View.VISIBLE);
                        ladekreis.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
    public void setEntschuldigtBtn() {
        if (ab.isEntschuldigt()){
            exAnzeige.setText("Unentschuldigt");
        } else {
            exAnzeige.setText("Entschuldigt");
        }
    }
*/