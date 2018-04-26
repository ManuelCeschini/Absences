package com.example.liquidsoftware.absences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity{
    SharedPreferences setPrefs;
    SharedPreferences getPrefs;
    private boolean activeUserLog = false;
    private EditText email;
    private EditText password;
    private String emailString;
    private String passwordString;
    Button login;
    Button register;
    AbsencesClient ac;
    Schueler s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setPrefs = this.getApplicationContext().getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        getPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //buttons
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        //Login Fields
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);



        getpreferences();
        if (activeUserLog == true) {
            System.out.println("-----------------------fastLogin check");
            fastLogin();
        }
            login();
            register();
    }

    private void setpreferences(){
        SharedPreferences.Editor editor = setPrefs.edit();
        editor.putString("Email", emailString);
        editor.putString("Password", passwordString);
        //editor.clear();
        //editor.commit();
        editor.apply();
    }

    private void getpreferences(){
        String email = getPrefs.getString("Email", "");
        String password = getPrefs.getString("Password", "");
        if(!email.equalsIgnoreCase("") && !password.equalsIgnoreCase(""))
        {
            activeUserLog = true;
        }
    }
    public void fastLogin(){
        emailString = getPrefs.getString("Email","");
        passwordString = getPrefs.getString("Password","");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("email", emailString);
        rp.put("passwort", passwordString);

        client.post("http://absences.bplaced.net/Absences_Webservice/login.php", rp, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (response != null) {
                    try {
                        s = Schueler.fromJSON(response);
                        Toast.makeText(Login.super.getApplicationContext(), "Login erfolgreich. Benutzer " + s.getVorname(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        //setpreferences();
                        intent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
                        intent.putExtra("email", emailString);
                        intent.putExtra("password", passwordString);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(Login.super.getApplicationContext(), "Login fehlgeschlagen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = email.getText().toString();
                passwordString = password.getText().toString();
                emailString = prepareString(emailString);
                System.out.println("------------------------ email: " + emailString);
                //emailString.trim();
                System.out.println("-----------------------------login check");
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams rp = new RequestParams();
                rp.put("email", emailString);
                rp.put("passwort", passwordString);
                client.clearCredentialsProvider();
                client.post("http://absences.bplaced.net/Absences_Webservice/login.php", rp, new JsonHttpResponseHandler(){
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        System.out.println("---------------------- Response: " + response);
                        if (response != null) {
                            try {
                                s = Schueler.fromJSON(response);
                                Toast.makeText(Login.super.getApplicationContext(), "Login erfolgreich. Benutzer " + s.getVorname(), Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                intent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
                                intent.putExtra("id", s.getSchueler_id());
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(Login.super.getApplicationContext(), "Login fehlgeschlagen", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    public void register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Register");
                startActivity(intent);
            }
        });
    }

    public String prepareString(String email) {
        email = email.toLowerCase();
        email = email.trim();
        return email;
    }
}
