package com.example.liquidsoftware.absences;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    Button register;
    Button registerAbbrechen;
    AbsencesClient kc;

    private EditText name;
    private EditText nachname;
    private TextView geburtsdatum;
    private EditText email;
    private EditText password;
    private EditText rePassword;
    private Spinner klasseId;
    private ProgressBar pb_register;
    private ScrollView sv_r;
    private SwipeRefreshLayout srl_r;
    private String nameString;
    private String nachnameString;
    private String geburtsdatumString;
    private String emailString;
    private String passwordString;
    private String rePasswordString;
    private int klasseIdInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //TODO Jürgen: Registerklasse beenden
        pb_register = findViewById(R.id.ladekreis_register);
        srl_r = findViewById(R.id.swipe_refresh_layout_register);
        srl_r.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchKlassen();
            }
        });
        sv_r = findViewById(R.id.scrollview_register);
        kc = new AbsencesClient();
        name = findViewById(R.id.re_vorname);
        nachname = findViewById(R.id.re_nachname);
        geburtsdatum = findViewById(R.id.re_geburtsdatum);
        register = findViewById(R.id.re_register);
        registerAbbrechen = findViewById(R.id.registerAbbrechen);
        email = findViewById(R.id.re_email);
        password = findViewById(R.id.re_password);
        rePassword = findViewById(R.id.re_password_wiederholung);
        klasseId = findViewById(R.id.sv_klasse);
        setBirthdayTimePicker();
        fetchKlassen();
    }

    public String prepareString(String email){
        email = email.toLowerCase();
        email = email.trim();
        return email;
    }

    public void register(ArrayList<String> classes) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        klasseId.setAdapter(dataAdapter);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = name.getText().toString();
                nachnameString = nachname.getText().toString();
                nameString = prepareString(nameString);
                geburtsdatumString = geburtsdatum.getText().toString();
                emailString = email.getText().toString();
                passwordString = password.getText().toString();
                rePasswordString = rePassword.getText().toString();
                klasseIdInt = klasseId.getSelectedItemPosition();
                klasseIdInt += 1;

                if (passwordString.equals(rePasswordString)) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams rp = new RequestParams();
                    rp.put("vorname", nameString);
                    rp.put("nachname", nachnameString);
                    rp.put("geburtstag", geburtsdatumString);
                    rp.put("email", emailString);
                    rp.put("passwort", passwordString);
                    rp.put("klasse_id", klasseIdInt);
                    client.post("http://absences.bplaced.net/Absences_Webservice/register.php", rp, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(Register.super.getApplicationContext(), "Ein Fehler ist aufgetreten", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            Toast.makeText(Register.super.getApplicationContext(), "Registrierung erfolgreich", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        registerAbbrechen.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void fetchKlassen() {
        kc.getAbsence(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray arr = null;
                ArrayList<Klasse> classes;
                ArrayList<String> classesStr = new ArrayList<>();
                if (response != null) {
                    try {
                        arr = response.getJSONArray("klasse");
                        classes = Klasse.fromJSON(arr);
                        for (Klasse k : classes) {
                            classesStr.add(k.getKlassenname());
                        }
                        register(classesStr);
                        srl_r.setRefreshing(false);
                        srl_r.setEnabled(false);
                        pb_register.setVisibility(View.GONE);
                        sv_r.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        srl_r.setRefreshing(false);
                        pb_register.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(Register.super.getApplicationContext(), "Laden der Daten fehlgeschlagen", Toast.LENGTH_SHORT).show();
                srl_r.setRefreshing(false);
                pb_register.setVisibility(View.GONE);
            }
        }, "klasse",0);
    }
    public void setBirthdayTimePicker(){
        geburtsdatum.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                int month = mcurrentDate.get(Calendar.MONTH);
                int year = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog mDatePicker;

                mDatePicker = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int[] params = new int[3];
                        params[0] = String.valueOf(i).length();
                        params[1] = String.valueOf(i1).length();
                        params[2] = String.valueOf(i2).length();

                        String z0 = "";
                        String z1 = "";
                        String z2 = "";

                        if (params[0] == 1){
                            z0 = "0";
                        }
                        if (params[1] == 1){
                            z1 = "0";
                        }
                        if (params[2] == 1){
                            z2 = "0";
                        }
                        i1 = i1 + 1;
                        geburtsdatum.setText(z2 + i2 + "/" + z1 + i1 + "/" + z0 + i);

                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });
    }
}
