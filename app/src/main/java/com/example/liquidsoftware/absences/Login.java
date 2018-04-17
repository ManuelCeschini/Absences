package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private String emailString;
    private String passwordString;
    Button login;
    Button register;


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


        //login();
        debug();
        register();
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
