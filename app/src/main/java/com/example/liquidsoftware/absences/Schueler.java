package com.example.liquidsoftware.absences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Juergen on 24.03.2018.
 */

public class Schueler {
    private int schueler_id;
    private String vorname;
    private String nachname;
    private String geburtstag;
    private String email;
    private String passwort;
    private int klasse_id;

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getKlasse_id() {
        return klasse_id;
    }

    public void setKlasse_id(int klasse_id) {
        this.klasse_id = klasse_id;
    }

    public int getSchueler_id() {
        return schueler_id;
    }

    public void setSchueler_id(int schueler_id) {
        this.schueler_id = schueler_id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Schueler fromJSON(JSONObject o){
        Schueler sch = new Schueler();
        try {
            sch.schueler_id = o.getInt("schueler_id");
            sch.vorname = o.getString("vorname");
            sch.nachname = o.getString("nachname");
            sch.geburtstag = o.getString("geburtstag");
            sch.email = o.getString("email");
            sch.passwort = o.getString("passwort");
            sch.klasse_id = o.getInt("klasse_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sch;
    }
}
