package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Absence ab;
    AbsencesClient ac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ab = new Absence();
        ac = new AbsencesClient();
        Intent ii = getIntent();
        Bundle bundle = ii.getExtras();
        if(bundle != null){
            id = bundle.getInt("position") + 1;
        }
        System.out.println("The given id is: " + id);

        try {
            fetchAbsenzen();
        }catch (Exception e){
            System.out.println("Fail to lad fetchAbsenzen in Anzeige: " + e);
        }
        titel = (TextView)findViewById(R.id.AnzeigeTitel);
        datumanfang = (TextView)findViewById(R.id.AnzeigeDatumStart);
        datumende = (TextView)findViewById(R.id.AnzeigeDatumEnde);
        begruendung = (TextView)findViewById(R.id.AnzeigeBegruendung);

        try {
            titel.setText(ab.getTitel());
            datumanfang.setText(ab.getDatum_beginn());
            datumende.setText(ab.getDatum_ende());
            begruendung.setText(ab.getGrund());
        }catch (Exception e){
            System.out.println("Fail to load params in Anzeige: " + e);
        }
    }
    public void fetchAbsenzen(){
        ac.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    try {
                        Absence a = Absence.fromJSON(response);
                        ab = a;
                        Log.w("JSON","" +a.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, id);
    }
}
