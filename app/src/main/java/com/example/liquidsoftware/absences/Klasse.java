package com.example.liquidsoftware.absences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Juergen on 24.03.2018.
 */

public class Klasse {
    private int klasse_id;
    private String klassenname;

    public int getKlasse_id() {
        return klasse_id;
    }

    public void setKlasse_id(int klasse_id) {
        this.klasse_id = klasse_id;
    }

    public String getKlassenname() {
        return klassenname;
    }

    public void setKlassenname(String klassenname) {
        this.klassenname = klassenname;
    }

    public static Klasse fromJSON(JSONObject o){
        Klasse kla = new Klasse();
        try {
            kla.klasse_id = o.getInt("klasse_id");
            kla.klassenname = o.getString("klassenname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kla;
    }

    public static ArrayList<Klasse> fromJSON(JSONArray jsonArray) {
        ArrayList<Klasse> classes = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Klasse k = fromJSON(o);
                classes.add(k);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
