package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    ImageButton mainButton;

    ImageButton clickButton;

    ImageButton leftButton;
    ImageButton rightButton;

    Integer x = 0;
    Animation animation;

    Boolean check = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        leftButton = findViewById(R.id.buttonLeft);
        rightButton = findViewById(R.id.buttonRight);

        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

        textView = findViewById(R.id.textView2);

        mainButton = findViewById(R.id.MainButton);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);

        textView.setText(" ");


        clickButton = findViewById(R.id.clickButton);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                textView.setText(x.toString());
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLeft:
                Intent intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonRight:
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }

}