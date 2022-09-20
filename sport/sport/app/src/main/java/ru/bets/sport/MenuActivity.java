package ru.bets.sport;



import static ru.bets.sport.MainActivity.APP_PREFERENCES_BACKGROUND;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_TEXTCOLOR;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MenuActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    Button btn1, btn2, btn3, btn4;

    public static final String APP_PREFERENCES = "mysettings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        linearLayout = findViewById(R.id.linear);
        btn1 = findViewById(R.id.butTables);
        btn2 = findViewById(R.id.butNews);
        btn3 = findViewById(R.id.butStats);
        btn4 = findViewById(R.id.butSettings);


    }

    @Override
    protected void onStart() {
        super.onStart();

        linearLayout = findViewById(R.id.linear);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSettings.contains(APP_PREFERENCES_BACKGROUND)) {
            myBg = mSettings.getString(APP_PREFERENCES_BACKGROUND, "");
        }
        int resID = getResources().getIdentifier(myBg , "drawable", getPackageName());
        linearLayout.setBackgroundResource(resID);
        btn1.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn3.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn4.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));



    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butTables:
                Intent intent = new Intent(MenuActivity.this, CountryActivityTable.class);
                startActivity(intent);
                break;
            case R.id.butSettings:
                Intent intent1 = new Intent(MenuActivity.this, SettingsActivity.class);
                startActivity(intent1);
                break;
            case R.id.butStats:
                Intent intent2 = new Intent(MenuActivity.this, StatsActivity.class);
                startActivity(intent2);
                break;
            case R.id.butNews:
                Intent intent3 = new Intent(MenuActivity.this,NewsActivity.class);
                startActivity(intent3);
        }



    }









}