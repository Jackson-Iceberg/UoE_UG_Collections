package com.example.e_cclesia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class activaty_Login extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activaty_login);

        radioGroup = findViewById(R.id.rg_mode);
        radioButton1 = findViewById(R.id.rb_EA);
        radioButton2 = findViewById(R.id.rb_EO);

    }
}