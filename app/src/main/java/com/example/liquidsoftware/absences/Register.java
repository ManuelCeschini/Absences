package com.example.liquidsoftware.absences;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    Button register;
    Button registerAbbrechen;

    private EditText name;
    private EditText nachname;
    private TextView geburtsdatum;
    private EditText email;
    private EditText password;
    private EditText RePassword;
    private String nameString;
    private String nachnameString;
    private String geburtsdatumString;
    private String emailString;
    private String PasswordString;
    private String rePasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.re_vorname);
        nachname = findViewById(R.id.re_nachname);
        geburtsdatum = findViewById(R.id.re_geburtsdatum);
        register = findViewById(R.id.re_register);
        registerAbbrechen = findViewById(R.id.registerAbbrechen);
        email = findViewById(R.id.re_email);
        password = findViewById(R.id.re_password);
        RePassword = findViewById(R.id.re_password_wiederholung);
        register();
        gebDatum();
    }
    public void gebDatum(){
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

    public void register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = name.getText().toString();
                nachnameString = nachname.getText().toString();
                geburtsdatumString = geburtsdatum.getText().toString();
                emailString = email.getText().toString();
                PasswordString = password.getText().toString();
                rePasswordString = RePassword.getText().toString();


                //übergabe Parameter
                // Überprüfe ob es email schon gibt

                if (PasswordString.equals(rePasswordString)) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams rp = new RequestParams();
                    rp.put("email", emailString);
                    rp.put("passwort", PasswordString);

                    client.post("http://absences.bplaced.net/Absences_Webservice/register.php", rp, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(Register.super.getApplicationContext(), "Nicht geklappt: " + responseString, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            Toast.makeText(Register.super.getApplicationContext(), "Geklappt: " + responseString, Toast.LENGTH_LONG).show();
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
}
