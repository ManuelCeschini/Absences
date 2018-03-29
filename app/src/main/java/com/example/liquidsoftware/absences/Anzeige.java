package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Anzeige extends AppCompatActivity {

    TextView titel;
    TextView datumanfang;
    TextView datumende;
    TextView begruendung;
    Absence ab;
    int id;

    public Anzeige(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titel = (TextView)findViewById(R.id.AnzeigeTitel);
        datumanfang = (TextView)findViewById(R.id.AnzeigeDatumStart);
        datumende = (TextView)findViewById(R.id.AnzeigeDatumEnde);
        begruendung = (TextView)findViewById(R.id.AnzeigeBegruendung);

        Intent ii = getIntent();
        Bundle bundle = ii.getExtras();
        if(bundle != null){
            id = bundle.getInt("position");
        }
        System.out.println("The given id is: " + id);
        ab = new Absence();

        try {
            ab.setId(id);
            titel.setText(ab.getTitel());
            datumanfang.setText(ab.getDatum_beginn());
            datumende.setText(ab.getDatum_ende());
            begruendung.setText(ab.getGrund());
        }catch(Exception e){
            System.out.println("Fail load params from Anzeige");
        }
    }
}
