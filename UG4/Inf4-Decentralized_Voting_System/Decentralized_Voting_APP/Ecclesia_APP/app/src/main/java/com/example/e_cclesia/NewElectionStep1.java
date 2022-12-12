package com.example.e_cclesia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class NewElectionStep1 extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static Button btnStart;
    @SuppressLint("StaticFieldLeak")
    private static Button btnEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_election_step1);
        btnStart = findViewById(R.id.RegisterStart);
        btnEnd = findViewById(R.id.RegisterEnd);
        Button back = findViewById(R.id.ElectionBackHomeButton);
        Button cancel = findViewById(R.id.ElectionCancelButton);
        Button next = findViewById(R.id.NextButton1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep1.this,NewElectionStep2.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogStart(v);

            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogEnd(v);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep1.this,MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep1.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public static class DatePickerFragmentStart extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String str = day + "/" + (int) (month+1)+ "/" + year;
            btnStart.setText(str);
        }
    }

    public void showDatePickerDialogStart(View v) {
        DialogFragment newFragment = new DatePickerFragmentStart();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
    public static class DatePickerFragmentEnd extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String str = day + "/" + (int) (month+1) + "/" + year;
            btnEnd.setText(str);
        }
    }

    public void showDatePickerDialogEnd(View v) {
        DialogFragment newFragment = new DatePickerFragmentEnd();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }
}