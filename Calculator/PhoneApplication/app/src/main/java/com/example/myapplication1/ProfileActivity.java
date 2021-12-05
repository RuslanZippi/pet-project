package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton mainButton;
    ImageButton leftButton;
    ImageButton rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        mainButton = findViewById(R.id.mainButtonProfile);
        leftButton = findViewById(R.id.buttonLeftProfile);
        rightButton = findViewById(R.id.buttonRightProfile);

        mainButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLeftProfile:
                Intent intent2 = new Intent(this, CalendarActivity.class);
                startActivity(intent2);
                break;
            case R.id.mainButtonProfile:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
            default:
                break;

        }
    }
}