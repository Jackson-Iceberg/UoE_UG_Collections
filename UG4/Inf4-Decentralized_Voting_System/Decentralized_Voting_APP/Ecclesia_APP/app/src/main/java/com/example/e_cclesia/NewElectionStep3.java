package com.example.e_cclesia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class NewElectionStep3 extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    private static Button btnStart;
    @SuppressLint("StaticFieldLeak")
    private static Button btnEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_election_step3);
        btnStart = findViewById(R.id.VotingStart);
        btnEnd = findViewById(R.id.VotingEnd);
        Button back = findViewById(R.id.ElectionBackHomeButton3);
        Button cancel = findViewById(R.id.ElectionCancelButton3);
        Button next = findViewById(R.id.NextButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep3.this,NewElectionStep2.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep3.this,MainActivity.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep3.this,ElectionSummary.class);
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

        RecyclerView votersRecView = findViewById(R.id.VotersRecView);
        ArrayList<Voter> votersList = new ArrayList<>();
        votersList.add(new Voter("Zhou Hao","100"));
        votersList.add(new Voter("Ivan Khan","200"));
        votersList.add(new Voter("Frederick Parker","300"));
        votersList.add(new Voter("Melody Poole","400"));
        votersList.add(new Voter("Tia Andrews","500"));
        votersList.add(new Voter("Alina Cunningham","600"));
        votersList.add(new Voter("Jaime Nicholson","500"));
        votersList.add(new Voter("Aiden Edwards","600"));

        VotersRecViewAdapter adapter = new VotersRecViewAdapter();
        adapter.setVotersList(votersList);
        votersRecView.setAdapter(adapter);
        votersRecView.setLayoutManager(new LinearLayoutManager(this));
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
        DialogFragment newFragment = new NewElectionStep3.DatePickerFragmentStart();
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
        DialogFragment newFragment = new NewElectionStep3.DatePickerFragmentEnd();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }



}