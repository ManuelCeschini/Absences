package com.example.liquidsoftware.absences;

/**
 * Created by Manuel on 07.03.2018.
 */

public interface connect_interface {

    //get Schueler
    public void getSchuelerId();
    public void getSchuelerVorname();
    public void getSchuelerNachname();
    public void getSchuelerGeburtstag();
    public void getSchuelerEmail();

    //set Schueler
    public int setSchuelerId();
    public String setSchuelerVorname();
    public String setSchuelerNachname();
    public long setSchuelerGeburtstag();
    public String setSchuelerEmail();

    //get Absenzen
    public void getAbsenzenId();
    public void getTitel();
    public void getDatumBeginn();
    public void getDatumEnde();
    public void getGrund();

    //set Absenzen
    public int setAbsenzenId();
    public String setTitel();
    public long setDatumBeginn();
    public long setDatumEnde();
    public String setGrund();

    //set Klassenlehrer
    public int setKlassenlehrerId();
    public String setLehrerVorname();
    public String setLehrerNachname();
    public String setLehrerEmail();

    //get Klassenlehrer
    public void getKlassenlehrerId();
    public void getLehrerVorname();
    public void getLehrerNachname();
    public void getLehrerEmail();

    //set Klasse
    public int setKlassenId();
    public String setKlassenName();

    //get Klasse
    public void getKlassenId();
    public void getKlassenName();

}
