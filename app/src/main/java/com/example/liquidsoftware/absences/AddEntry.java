package com.example.liquidsoftware.absences;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddEntry extends AppCompatActivity {
    public EditText titleText;
    public TextView dateBegin;
    public TextView dateEnd;
    public TextView timeBegin;
    public TextView timeEnd;
    public Button bt;
    public Button btAb;

    public String dateBeginString;
    public String dateEndString;
    public String timeBeginString;
    public String timeEndString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        titleText = (EditText) findViewById(R.id.Titletext);
        dateBegin = (TextView) findViewById(R.id.dateBegin);
        dateEnd =   (TextView) findViewById(R.id.dateEnd);
        timeBegin = (TextView) findViewById(R.id.timeBegin);
        timeEnd =   (TextView) findViewById(R.id.timeEnd);
        bt =        (Button)   findViewById(R.id.newEntry);
        btAb =      (Button)   findViewById(R.id.entryAbbrechen);

        /*
        timeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("manuellllo", "hallo");
            }
        });
        */
        dBegin();
        dEnd();
        tBegin();
        tEnd();
        button();

    }

    public void button(){
        timeBegin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dateBeginString = dateBegin.getText().toString();
                dateEndString = dateEnd.getText().toString();
                timeBeginString = timeBegin.getText().toString();
                timeEndString = timeEnd.getText().toString();
                //Übergabe Parameter
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
                        timeBegin.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.setTitle("Select Time");
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
                        timeEnd.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.setTitle("Select Time");
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
                        dateBegin.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, day, month, year);
                mDatePicker.setTitle("Select Time");
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
                            dateEnd.setText(i2 + "/" + i1 + "/" + i);
                        }
                    }, day, month, year);
                    mDatePicker.setTitle("Select Time");
                    mDatePicker.show();
                }
            });
    }
}
