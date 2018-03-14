package com.example.liquidsoftware.absences;

/**
 * Created by Manuel on 07.03.2018.
 */

public interface connect_interface {

    //get Schueler
    public void getSchuelerId();
    public void getVorname();
    public void getNachname();
    public void getGeburtstag();
    public void getEmail();

    //set Schueler
    public int setSchuelerId();
    public String setVorname();
    public String setNachname();
    public long setGeburtstag();
    public String setEmail();

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




}
