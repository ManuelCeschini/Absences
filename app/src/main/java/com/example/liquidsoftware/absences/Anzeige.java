package com.example.liquidsoftware.absences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Anzeige extends AppCompatActivity {

    TextView titel;
    TextView datumanfang;
    TextView datumende;
    TextView begruendung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeige);

        titel = (TextView)findViewById(R.id.AnzeigeTitel);
        datumanfang = (TextView)findViewById(R.id.AnzeigeDatumStart);
        datumende = (TextView)findViewById(R.id.AnzeigeDatumEnde);
        begruendung = (TextView)findViewById(R.id.AnzeigeBegruendung);
    }
}
