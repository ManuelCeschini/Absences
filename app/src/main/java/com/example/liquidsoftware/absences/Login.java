package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
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

        //buttons
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        //Login Fields
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);


        login();
        //debug();
        //register();
    }

    public void debug(){
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
        startActivity(intent);
    }

    public void login() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = email.getText().toString();
                passwordString = password.getText().toString();

                //übergabe Parameter

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams rp = new RequestParams();
                rp.put("email", emailString);
                rp.put("passwort", passwordString);

                client.post("http://absences.bplaced.net/Absences_Webservice/login.php", rp, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(Login.super.getApplicationContext(),"Nicht geklappt: " + responseString, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(Login.super.getApplicationContext(),"Geklappt: " + responseString, Toast.LENGTH_LONG).show();
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
}
