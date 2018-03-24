package com.example.liquidsoftware.absences;

/**
 * Created by Juergen on 24.03.2018.
 */

public class Klassenlehrer {
    private int lehrer_id;
    private String vorname;
    private String nachname;
    private String email;

    public int getLehrer_id() {
        return lehrer_id;
    }

    public void setLehrer_id(int lehrer_id) {
        this.lehrer_id = lehrer_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
