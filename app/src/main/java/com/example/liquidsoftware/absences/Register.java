package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Register extends AppCompatActivity {

    Button register;
    private EditText email;
    private EditText password;
    private EditText RePassword;
    private String emailString;
    private String PasswordString;
    private String rePasswordString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.re_register);
        email = findViewById(R.id.re_email);
        password = findViewById(R.id.re_password);
        RePassword = findViewById(R.id.re_password_wiederholung);
        register();
    }

    public void register() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }
}
