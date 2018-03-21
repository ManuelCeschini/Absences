package com.example.liquidsoftware.absences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Adapter ad;
    public Database_parameters pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pr = new Database_parameters();
        ArrayList items = new ArrayList();
        ad = new Adapter(this, items);

    }
}
