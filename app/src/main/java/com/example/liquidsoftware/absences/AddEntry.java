package com.example.liquidsoftware.absences;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEntry extends AppCompatActivity {
    public EditText dateBegin;
    public EditText dateEnd;
    public EditText timeBegin;
    public EditText timeEnd;
    public Button bt;

    public String dateBeginString;
    public String dateEndString;
    public String timeBeginString;
    public String timeEndString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        dateBegin = (EditText) findViewById(R.id.dateBegin);
        dateEnd = (EditText) findViewById(R.id.dateEnd);
        timeBegin = (EditText) findViewById(R.id.timeBegin);
        timeEnd = (EditText) findViewById(R.id.timeEnd);
        bt = (Button) findViewById(R.id.newEntry);

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
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntry.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        dateBegin.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
    public void dEnd(){
        dateEnd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEntry.this, new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        dateEnd.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//24 Stunden Format
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }
}
