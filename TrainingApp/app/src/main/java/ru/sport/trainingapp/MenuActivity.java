package ru.sport.trainingapp;

import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_NAME;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_SCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    Intent intent;
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        progressBar = findViewById(R.id.progressBar);
        textView =findViewById(R.id.progressText);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setProgress(mSettings.getInt(APP_PREFERENCES_SCORE,0));
        textView.setText(Html.fromHtml("<font color=#ffffff>ПРОГРЕСС: </font>" +
                "<font color=#e97216>" +
                mSettings.getInt(APP_PREFERENCES_SCORE,0) +
                "</font>" +"<font color=#ffffff>P</font>"));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butAnc:
                intent = new Intent(MenuActivity.this, AnaliticsActivity.class);
                break;
            case R.id.butTrain:
                intent = new Intent(MenuActivity.this, TrainActivity.class);
                break;
            case R.id.butSettings:
                intent = new Intent(MenuActivity.this, SettingsActivity.class);
                break;
            case R.id.butTrener:
                intent = new Intent(MenuActivity.this, TrenerActivity.class);
        }
        startActivity(intent);
    }
}