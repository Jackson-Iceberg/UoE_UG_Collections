package com.example.e_cclesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button startElectionButton;
    private Button viewElectionButton;
    private Button helpButton;
    private Button contactButton;
//
//    private Button login;
//    private Button eaStartElectionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startElectionButton = findViewById(R.id.startElectionButton);
        viewElectionButton = findViewById(R.id.viewElectionButton);
        helpButton = findViewById(R.id.helpButton);
        contactButton = findViewById(R.id.contactButton);

//        login = findViewById(R.id.btn_login);
//        eaStartElectionButton = findViewById(R.id.btn_EA_StartElection);


        startElectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewElectionStep1.class);
                startActivity(intent);
            }
        });

        viewElectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewElection.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, HelpCenter.class);
                startActivity(intent);
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactUs.class);
                startActivity(intent);
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,activaty_Login.class);
//                startActivity(intent);
//            }
//        });

//        eaStartElectionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,EA_startElection.class);
//                startActivity(intent);
//            }
//        });

    }


}