package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                if (PasswordString == rePasswordString) { // Überprüfe ob es email schon gibt
                    //übergabe Parameter
                }

            }
        });
    }
}
