package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Options extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        Button simpleBTN = findViewById(R.id.simpleBTN);
        Button advancedBTN = findViewById(R.id.advancedBTN);
        Button aboutBTN = findViewById(R.id.aboutBTN);
        Button exitBTN = findViewById(R.id.exitBTN);

        simpleBTN.setOnClickListener(this);
        advancedBTN.setOnClickListener(this);
        aboutBTN.setOnClickListener(this);
        exitBTN.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simpleBTN:
                openSimpleCalculator();
                break;
            case R.id.advancedBTN:
                openAdvancedCalculator();
                break;
            case R.id.aboutBTN:
                openAbout();
                break;
            case R.id.exitBTN:
                finish();
                System.exit(0);
                break;
        }
    }

    public void openSimpleCalculator() {
        Intent intent = new Intent(this, SimpleCalc.class);
        startActivity(intent);
    }

    public void openAbout() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void openAdvancedCalculator() {
        Intent intent = new Intent(this, AdvancedCalc.class);
        startActivity(intent);
    }
}
