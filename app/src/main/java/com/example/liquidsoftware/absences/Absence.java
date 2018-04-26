package com.example.liquidsoftware.absences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Manuel on 21.03.2018.
 */

public class Absence {
    private int id;
    private String titel;
    private Date datum_beginn;
    private Date datum_ende;
    private String grund;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Date d;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Date getDatum_beginn() {
        return datum_beginn;
    }

    public void setDatum_beginn(Date datum_beginn) {
        this.datum_beginn = datum_beginn;
    }

    public Date getDatum_ende() {
        return datum_ende;
    }

    public void setDatum_ende(Date datum_ende) {
        this.datum_ende = datum_ende;
    }

    public String getGrund() {
        return grund;
    }

    public void setGrund(String grund) {
        this.grund = grund;
    }


    public static Absence fromJSON(JSONObject o){
        Absence abs = new Absence();
        try {
            abs.id = o.getInt("absenz_id");
            abs.titel = o.getString("titel");
            abs.grund = o.getString("grund");
            d = sdf.parse(o.getString("datum_beginn"));
            abs.datum_beginn = d;
            d = sdf.parse(o.getString("datum_ende"));
            abs.datum_ende = d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return abs;
    }

    public static ArrayList<Absence> fromJSON(JSONArray jsonArray) {
        ArrayList<Absence> absences = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Absence b = fromJSON(o);
                absences.add(b);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return absences;
    }
}