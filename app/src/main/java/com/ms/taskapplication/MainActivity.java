package com.ms.taskapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText mon, year;
    Button show;
    TextView age;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mon = findViewById(R.id.month);
        year = findViewById(R.id.year);
        show = findViewById(R.id.show);
        age = findViewById(R.id.showText);


        if(savedInstanceState!=null) {
            age.setText(savedInstanceState.getString("age"));
        }

        show.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                LocalDate today = LocalDate.now();
                if (year.getText().toString().equals("") || mon.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this,getString(R.string.invalid), Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(year.getText().toString()) > today.getYear()){
                    Toast.makeText(MainActivity.this, getString(R.string.invalidyearormonth), Toast.LENGTH_SHORT).show();
                } else if(Integer.parseInt(mon.getText().toString()) > 12){
                    Toast.makeText(MainActivity.this, getString(R.string.invalidyearormonth), Toast.LENGTH_SHORT).show();
                } else {
                    LocalDate birthday = LocalDate.of(Integer.parseInt(year.getText().toString()),
                            Integer.parseInt(mon.getText().toString()),1);
                    Period p = Period.between(birthday, today);
                    System.out.println(p.getMonths());
                    if (p.getMonths() == 0) {
                        if (Integer.parseInt(mon.getText().toString()) > today.getMonthValue()){
                            Toast.makeText(MainActivity.this, getString(R.string.invalidyearormonth), Toast.LENGTH_SHORT).show();
                        } else {
                        age.setText(getString(R.string.your_age_is) + " " +
                                p.getYears() + " " + getString(R.string.year));
                        }
                    }else {
                        age.setText(getString(R.string.your_age_is) +
                                " " + p.getYears() + " " + getString(R.string.year) + " " +
                                getString(R.string.and) + " " + p.getMonths() +
                                " " + getString(R.string.month));
                    }
                }
            }
        });





    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("age", age.getText().toString());
        // etc.
    }
}