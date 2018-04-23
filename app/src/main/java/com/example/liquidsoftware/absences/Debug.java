package com.example.liquidsoftware.absences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class Debug extends AppCompatActivity {

    Button bt0;
    Button bt1;
    Button bt2;
    Button bt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        bt0 = (Button) findViewById(R.id.debugBt0);
        bt1 = (Button) findViewById(R.id.debugBt1);
        bt2 = (Button) findViewById(R.id.debugBt2);
        bt3 = (Button) findViewById(R.id.debugBt3);
        btns();
    }

    public void btns() {
        bt0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Login");
                startActivity(intent);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".Register");
                startActivity(intent);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".MainActivity");
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName() + ".AddEntry");
                startActivity(intent);
            }
        });
    }
}
