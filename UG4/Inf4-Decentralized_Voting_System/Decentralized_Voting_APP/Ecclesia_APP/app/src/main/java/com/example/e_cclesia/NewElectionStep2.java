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


public class NewElectionStep2 extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static Button resultReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_election_step2);
        Button back = findViewById(R.id.ElectionBackHomeButton2);
        Button cancel = findViewById(R.id.ElectionCancelButton2);
        Button next = findViewById(R.id.NextButton2);
        resultReleaseDate = findViewById(R.id.ResultReleaseDate);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep2.this,NewElectionStep1.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewElectionStep2.this,NewElectionStep3.class);
                startActivity(intent);
            }
        });
        resultReleaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogStart(v);
            }
        });



        RecyclerView candidatesRecView = findViewById(R.id.CandidatesRecView);
        ArrayList<Candidate> candidatesList = new ArrayList<>();
        candidatesList.add(new Candidate("Boris Johnson",1964,"Male","001"));
        candidatesList.add(new Candidate("Theresa May",1956,"Female","002"));
        candidatesList.add(new Candidate("David Cameron",1966,"Male","003"));
        candidatesList.add(new Candidate("Gordon Brown",1951,"Male","004"));

        CandidatesRecViewAdapter adapter = new CandidatesRecViewAdapter();
        adapter.setCandidatesList(candidatesList);

        candidatesRecView.setAdapter(adapter);
        candidatesRecView.setLayoutManager(new LinearLayoutManager(this));
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
            String str = day + "/" + (int) (month+1) + "/" + year;
            resultReleaseDate.setText(str);
        }
    }

    public void showDatePickerDialogStart(View v) {
        DialogFragment newFragment = new NewElectionStep2.DatePickerFragmentStart();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }





}