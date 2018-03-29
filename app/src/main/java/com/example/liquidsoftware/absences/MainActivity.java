package com.example.liquidsoftware.absences;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    View convertView;
    ListView lv;
    AbsencesClient ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView1);
        ac = new AbsencesClient();
        ArrayList<Absence> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(new Absence());
        }
        try {
            //arr = fetchAbsenzen();
        }catch (Exception e){
            System.out.println("Fail to load params fron fetchAbsenzen");
        }

        Adapter adapter = new Adapter(this, arr);
        lv.setAdapter(adapter);
        anzeige();
        actionButton();
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

    public void actionButton(){
        FloatingActionButton abt = findViewById(R.id.actionButton);
        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO actionButton action
            }
        });
    }

    ArrayList<Absence> absences = new ArrayList<>();
    public ArrayList<Absence> fetchAbsenzen() {
        ac.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray arr = null;
                if (response != null) {
                    try {
                        arr = response.getJSONArray("features");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    absences = Absence.fromJSON(arr);
                }
            }
        });
        return absences;
    }
}

