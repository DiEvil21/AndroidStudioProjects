package ru.sport.trainingapp;

import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_NAME;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_HEIGHT;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_WEIGHT;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_SCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnaliticsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    EditText editTextR, editTextP;
    ProgressBar progressBar;
    TextView textView;
    int m = 0;
    int pr = 0;
    int weight;
    int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analitics);
        //----------------------------------------------
        editTextP = findViewById(R.id.editTextP);
        editTextR = findViewById(R.id.editTextR);
        progressBar= findViewById(R.id.progressBar);
        textView = findViewById(R.id.progressText);
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();
        //----------------------------------------------

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

    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.button3:
                if (editTextR.getText().toString().equals("") || editTextP.getText().toString().equals("") )
                {
                    System.out.println("пусто");
                }
                else
                {
                    weight = Integer.parseInt(mSettings.getString(APP_PREFERENCES_WEIGHT,""));
                    m = Integer.parseInt(editTextR.getText().toString());
                    pr = Integer.parseInt(editTextP.getText().toString());
                    res = ((m + pr*10)*weight)/10;
                    int prog = progressBar.getProgress() + res;
                    editor.putInt(APP_PREFERENCES_SCORE, prog);
                    progressBar.setProgress(progressBar.getProgress() + res);
                    String text = "<font color=" + "#e97216" + ">" + prog + "</font>";
                    textView.setText(Html.fromHtml("<font color=#ffffff>ПРОГРЕСС: </font>" +
                            "<font color=#e97216>" +
                            prog +
                            "</font>" +"<font color=#ffffff>P</font>"));
                    editor.apply();
                }





        }
    }
}