package com.example.liquidsoftware.absences;

/**
 * Created by Manuel on 07.03.2018.
 */

public interface Connect_interface {

    //set Schueler
    public void setSchuelerId(int id);
    public void setSchuelerVorname(String vorname);
    public void setSchuelerNachname(String nachname);
    public void setSchuelerGeburtstag(String geburtstag);
    public void setSchuelerEmail(String email);

    //get Schueler
    public int getSchuelerId();
    public String getSchuelerVorname();
    public String getSchuelerNachname();
    public String getSchuelerEmail();

    //set Absenzen
    public void setAbsenzenId(int id);
    public void setTitel(String titel);
    public void setDatumBeginn(String datum);
    public void setDatumEnde(String datum);
    public void setGrund(String grund);

    //get Absenzen
    public int getAbsenzenId();
    public String getTitel();
    public String getDatumBeginn();
    public String getDatumEnde();
    public String getGrund();

    //set Klassenlehrer
    public void setKlassenlehrerId(int id);
    public void setLehrerVorname(String vorname);
    public void setLehrerNachname(String nachname);
    public void setLehrerEmail(String email);

    //get Klassenlehrer
    public int getKlassenlehrerId();
    public String getLehrerVorname();
    public String getLehrerNachname();
    public String getLehrerEmail();

    //set Klasse
    public void setKlassenId(int id);
    public void setKlassenName(String name);

    //get Klasse
    public int getKlassenId();
    public String getKlassenName();



}
