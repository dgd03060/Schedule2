package com.example.schedule2;



import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;

import android.widget.CalendarView;

import android.widget.Toast;





public class CalendarActivity extends Activity {



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_calendar);



        //CalendarView 인스턴스 만들기

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);



        //리스너 등록

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {



            @SuppressLint("WrongConstant")
            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month,

                                            int dayOfMonth) {

                // TODO Auto-generated method stub

                Toast.makeText(CalendarActivity.this, ""+year+"/"+(month+1)+"/"

                        +dayOfMonth, 0).show();

            }

        });



    }







}



