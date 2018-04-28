package com.example.liquidsoftware.absences;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class AddEntry extends AppCompatActivity {
    private EditText titelText;
    private EditText grund;
    private TextView dateBegin;
    private TextView dateEnd;
    private TextView timeBegin;
    private TextView timeEnd;
    private Button bt;
    private Button btAb;
    private RelativeLayout rl_ae;
    private ProgressBar pb_add_entry;

    private String dateBeginString;
    private String dateEndString;
    private String titelString;
    private String grundString;
    private int schuelerIdInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Intent intent = getIntent();
        schuelerIdInt = intent.getIntExtra("schueler_id", 0);
        if (schuelerIdInt == 0) {
            Toast.makeText(AddEntry.this.getApplicationContext(), "Du musst eingeloggt sein", Toast.LENGTH_SHORT).show();
            finish();
        }

        titelText = findViewById(R.id.titelText);
        grund = findViewById(R.id.grund);
        dateBegin = findViewById(R.id.dateBegin);
        dateEnd = findViewById(R.id.dateEnd);
        timeBegin = findViewById(R.id.timeBegin);
        timeEnd = findViewById(R.id.timeEnd);
        bt = findViewById(R.id.newEntry);
        btAb = findViewById(R.id.entryAbbrechen);
        rl_ae = findViewById(R.id.relative_layout_add_entry);
        pb_add_entry = findViewById(R.id.ladekreis_add_entry);

        dBegin();
        dEnd();
        tBegin();
        tEnd();
        button();
    }

    public void addNewEntry() {
        pb_add_entry.setVisibility(View.VISIBLE);
        rl_ae.setVisibility(View.GONE);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("titel", titelString);
        rp.put("grund", grundString);
        rp.put("datum_beginn", dateBeginString);
        rp.put("datum_ende", dateEndString);
        rp.put("schueler_id", schuelerIdInt);
        client.post("http://absences.bplaced.net/Absences_Webservice/addEntry.php", rp, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errMsg = "Es ist ein Fehler aufgetreten";
                if (errorResponse != null) {
                    try {
                        errMsg = errorResponse.getString("response");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                pb_add_entry.setVisibility(View.GONE);
                rl_ae.setVisibility(View.VISIBLE);
                Toast.makeText(AddEntry.super.getApplicationContext(), errMsg, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                String errMsg = "Es ist ein !JSON Fehler aufgetreten";
                pb_add_entry.setVisibility(View.GONE);
                rl_ae.setVisibility(View.VISIBLE);
                Toast.makeText(AddEntry.super.getApplicationContext(), errMsg, Toast.LENGTH_LONG).show();
            }
            //TODO Jürgen: Ladekreisbug fixen
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(AddEntry.super.getApplicationContext(), "Absenz hinzugefügt", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    public void button(){
        bt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                titelString = titelText.getText().toString();
                grundString = grund.getText().toString();
                dateBeginString = dateBegin.getText().toString() + " " + timeBegin.getText().toString();
                dateEndString = dateEnd.getText().toString() + " " + timeEnd.getText().toString();
                addNewEntry();
            }
        });

        btAb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void tBegin(){
        timeBegin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntry.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int[] params = new int[3];
                        params[0] = String.valueOf(selectedHour).length();
                        params[1] = String.valueOf(selectedMinute).length();

                        String z0 = "";
                        String z1 = "";

                        if (params[0] == 1){
                            z0 = "0";
                        }
                        if (params[1] == 1){
                            z1 = "0";
                        }
                        timeBegin.setText(z0 + selectedHour + ":" +  z1 + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.show();

            }
        });

    }
    public void tEnd(){
        timeEnd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntry.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int[] params = new int[3];
                        params[0] = String.valueOf(selectedHour).length();
                        params[1] = String.valueOf(selectedMinute).length();

                        String z0 = "";
                        String z1 = "";

                        if (params[0] == 1){
                            z0 = "0";
                        }
                        if (params[1] == 1){
                            z1 = "0";
                        }
                        timeEnd.setText(z0 + selectedHour + ":" +  z1 + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.show();

            }
        });
    }
    public void dBegin(){
        dateBegin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                int month = mcurrentDate.get(Calendar.MONTH);
                int year = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddEntry.this, new DatePickerDialog.OnDateSetListener() {
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
                        dateBegin.setText(z2 + i2 + "/" + z1 + i1 + "/" + z0 + i);
                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });

    }
    public void dEnd(){
        dateEnd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                int month = mcurrentDate.get(Calendar.MONTH);
                int year = mcurrentDate.get(Calendar.YEAR);
                DatePickerDialog mDatePicker;

                mDatePicker = new DatePickerDialog(AddEntry.this, new DatePickerDialog.OnDateSetListener() {
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
                        dateEnd.setText(z2 + i2 + "/" + z1 + i1 + "/" + z0 + i);

                    }
                }, year, month, day);
                mDatePicker.show();
            }
        });
    }
}
