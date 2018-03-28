package com.example.liquidsoftware.absences;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    private ListView listView;
     private Adapter adapter;
     //private Client client;*/
    View convertView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lv = (ListView)findViewById(R.id.listView1);
        ArrayList<Database_parameters> arr = new ArrayList<>();
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());
        arr.add(new Database_parameters());

        Adapter adapter = new Adapter(this,arr);
        lv.setAdapter(adapter);

        /*
        TextView datum;
        TextView temperatur;
        ImageView temperaturIcon;

        View convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_temperatur, viewGroup, false);
        datum = convertView.findViewById(R.id.datum);
        temperatur = convertView.findViewById(R.id.temperatur);
        temperaturIcon = convertView.findViewById(R.id.temperaturIcon);
        */


        /*
        ArrayList array = new ArrayList<>();
        listView.findViewById(R.id.list);
        adapter = new Adapter(this, array);
        listView.setAdapter(adapter);
        //takeItems('query')
        */
    }
    /*
    public void takeItems(String query){
        Client initialisieren;
        bookClient.getBooks(query, new JsonHttpResponseHandler() {
            public void onSuccess(int statuscode, Header[] headers, JSONObject response) {
                try {
                    JSONArray arr = null;
                    if (response != null) {
                        arr = response.getJSONArray("docs");
                        ArrayList<Database_parameters> books = Database_parameters.fromJSON(arr);
                        adapter.clear();
                        for (Database_parameters b : books) {
                            adapter.add(b);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        //});
        }
    */

}

