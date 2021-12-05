package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendarView;

    ImageButton mainButton;
    ImageButton leftButton;
    ImageButton rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getSupportActionBar().hide();
        calendarView = findViewById(R.id.calendarView);

        mainButton = findViewById(R.id.mainButtonCalendar);
        leftButton = findViewById(R.id.buttonLeftCalendar);
        rightButton = findViewById(R.id.buttonRightCalendar);

        mainButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dialogCalendarData();
            }
        });

    }

    private void dialogCalendarData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.custom_dialog,null);
        builder.setView(constraintLayout);
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonRightCalendar:
                Intent intent2 = new Intent(this, ProfileActivity.class);
                startActivity(intent2);
                break;
            case R.id.mainButtonCalendar:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
            default:
                break;
        }
    }
}