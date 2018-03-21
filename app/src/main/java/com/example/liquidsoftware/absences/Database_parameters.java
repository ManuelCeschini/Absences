package com.example.liquidsoftware.absences;

/**
 * Created by Manuel on 14.03.2018.
 */

public class Database_parameters implements Connect_interface {

    @Override
    public void setSchuelerId(int id) {

    }

    @Override
    public void setSchuelerVorname(String vorname) {

    }

    @Override
    public void setSchuelerNachname(String nachname) {

    }

    @Override
    public void setSchuelerGeburtstag(String geburtstag) {

    }

    @Override
    public void setSchuelerEmail(String email) {

    }

    @Override
    public int getSchuelerId() {
        return 0;
    }

    @Override
    public String getSchuelerVorname() {
        return null;
    }

    @Override
    public String getSchuelerNachname() {
        return null;
    }

    @Override
    public String getSchuelerEmail() {
        return null;
    }

    @Override
    public void setAbsenzenId(int id) {

    }

    @Override
    public void setTitel(String titel) {

    }

    @Override
    public void setDatumBeginn(String datum) {

    }

    @Override
    public void setDatumEnde(String datum) {

    }

    @Override
    public void setGrund(String grund) {

    }

    @Override
    public int getAbsenzenId() {
        return 1;
    }

    @Override
    public String getTitel() {
        return "AbsenzenTitel";
    }

    @Override
    public String getDatumBeginn() {
        return "01/01/17";
    }

    @Override
    public String getDatumEnde() {
        return "01/01/17";
    }

    @Override
    public String getGrund() {
        return "Faulheit";
    }

    @Override
    public void setKlassenlehrerId(int id) {

    }

    @Override
    public void setLehrerVorname(String vorname) {

    }

    @Override
    public void setLehrerNachname(String nachname) {

    }

    @Override
    public void setLehrerEmail(String email) {

    }

    @Override
    public int getKlassenlehrerId() {
        return 0;
    }

    @Override
    public String getLehrerVorname() {
        return null;
    }

    @Override
    public String getLehrerNachname() {
        return null;
    }

    @Override
    public String getLehrerEmail() {
        return null;
    }

    @Override
    public void setKlassenId(int id) {

    }

    @Override
    public void setKlassenName(String name) {

    }

    @Override
    public int getKlassenId() {
        return 0;
    }

    @Override
    public String getKlassenName() {
        return null;
    }
}
