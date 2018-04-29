package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    Button exAnzeige;
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
        exAnzeige = findViewById(R.id.exAnzeige);
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
            datumanfang.setText(sdf.format(ab.getDatum_beginn()));
            datumende.setText(sdf.format(ab.getDatum_ende()));
            begruendung.setText(ab.getGrund());

            textTitle.setText("Titel:");
            textDatumAnfang.setText("Von:");
            textDatumEnde.setText("Bis: ");
            textBegruendung.setText("Begründung:");

            exAnzeige.setVisibility(View.VISIBLE);
            deleteAnzeige.setVisibility(View.VISIBLE);
            deleteEntry();
            exAnzeige();


        }catch (Exception e){
            System.out.println("Failed to load params in Anzeige: " + e);
        }
    }
    public void exAnzeige(){
        if (true){ //TODO Jürgen: status der absenz, falls entschuldigt heißt der button "Unentschuldigt"
            exAnzeige.setText("Unentschuldigt");
        } else {
            exAnzeige.setText("Entschuldigt");
        }
        exAnzeige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO Jürgen: Button 1x gedrückt entschuldigt, 2x gedrückt wieder untentschuldigt, immer gegenteil als gespeichert*/
            }
        });
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
