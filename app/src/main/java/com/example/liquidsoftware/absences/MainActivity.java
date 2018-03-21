package com.example.liquidsoftware.absences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private ListView listView;
     private Adapter adapter;
     //private Client client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList array = new ArrayList<>();
        listView.findViewById(R.id.list);
        adapter = new Adapter(this, array);
        listView.setAdapter(adapter);
        //takeItems('query')
    }
    /*
    public void takeItems(String query){
        //Client initialisieren;
        //bookClient.getBooks(query, new JsonHttpResponseHandler() {
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

