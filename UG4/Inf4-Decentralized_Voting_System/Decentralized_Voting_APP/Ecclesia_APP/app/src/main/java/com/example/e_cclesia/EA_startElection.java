package com.example.e_cclesia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class EA_startElection extends AppCompatActivity {

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ea_start_election);

        addBtn = findViewById(R.id.addVoterBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EA_startElection.this,addVoter.class);
                startActivity(intent);
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
}
