package ru.dievil.mymath;


import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r;
    int progressStatus;
    private Handler handler = new Handler();
    Animation animation;
    RestartDialog restartDialog;
    private TextView answer;
    TextView exeption;
    ProgressBar bar;
    Boolean bool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        exeption = findViewById(R.id.textExeption);
        bar = findViewById(R.id.progressBar);
        answer = findViewById(R.id.answer);
        r = new Random();

        bool = setExeption();
        restartDialog = new RestartDialog();

        animation = AnimationUtils.loadAnimation(this, R.anim.textview_anim);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 150) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        public void run() {
                            bar.setProgress(100 - progressStatus);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (bar.getProgress() <= 0) {
                        System.out.println("Время Вышло(");

                        restartDialog.show(getSupportFragmentManager(),"custom");
                        break;
                    }
                }
            }
        }).start();


    }


    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.butYes:
                if (bool == true) {
                    System.out.println("Верно");
                    answer.setText("Верно");
                    answer.startAnimation(animation);
                    progressStatus -= 10;
                } else {
                    System.out.println("Ошибка");
                    answer.setText("Ошибка");
                    answer.startAnimation(animation);
                    progressStatus += 20;
                }
                break;
            case R.id.butNo:
                if (bool == true) {
                    System.out.println("Ошибка");
                    answer.setText("Ошибка");
                    answer.startAnimation(animation);
                    progressStatus += 20;
                } else {
                    System.out.println("Верно");
                    answer.setText("Верно");
                    answer.startAnimation(animation);
                    progressStatus -= 10;
                }
                break;
            case R.id.restartButton:
                restartDialog.dismiss();
                restartActivity(this);
        }
        bool = setExeption();

    }
    public boolean setExeption () {

        int f = r.nextInt(10 - 0);
        int s = r.nextInt(10 - 0);
        int res = f + s + (r.nextInt(5 - 0) - 4);
        exeption.setText(String.valueOf(f) + " + "+ String.valueOf(s) + " = " + String.valueOf(res));
        System.out.println(f + " + " + s + " = " + res);
        if (f + s == res) {
            return true;
        } else return false;

    }
    public static void restartActivity(Activity activity) {
        if (Build.VERSION.SDK_INT >= 11) {
            activity.recreate();
        } else {
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    }

}