package com.example.liquidsoftware.absences;

/**
 * Created by Juergen on 24.03.2018.
 */

public class Schueler {
    private int schueler_id;
    private String vorname;
    private String nachname;
    private String geburtstag;
    private String email;

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
}
