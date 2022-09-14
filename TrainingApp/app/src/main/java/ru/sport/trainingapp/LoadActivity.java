package ru.sport.trainingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.onesignal.OneSignal;

import java.util.Calendar;
import java.util.Objects;


public class LoadActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES_NAME = "myname";
    public static final String APP_PREFERENCES_HEIGHT = "myheight";
    public static final String APP_PREFERENCES_WEIGHT = "myweight";
    public static final String APP_PREFERENCES_SCORE = "myscore";
    public static final String APP_PREFERENCES_DAYOFWEEK = "myday";
    private static final String ONESIGNAL_APP_ID = "replace this";
    Intent intent;
    String dayOfWeek;
    Calendar calendar;
    int day;
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        // Enable verbose OneSignal logging to debug issues if needed.

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();

        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mSettings.getString(APP_PREFERENCES_NAME, "") != "" ) {
            System.out.println(mSettings.getString(APP_PREFERENCES_NAME, ""));
            intent = new Intent(LoadActivity.this, MenuActivity.class);
        } else {
            intent = new Intent(LoadActivity.this, LoginActivity.class);
        }

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                dayOfWeek = "sunday";
                break;
            case Calendar.MONDAY:
                dayOfWeek = "monday";
                break;
            case Calendar.TUESDAY:
                dayOfWeek = "tuesday";
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = "wednesday";
                break;
            case Calendar.THURSDAY:
                dayOfWeek = "thursday";
                break;
            case Calendar.FRIDAY:
                dayOfWeek = "friday";
                break;
            case Calendar.SATURDAY:
                dayOfWeek = "saturday";
                break;
        }
        if (!Objects.equals(mSettings.getString(APP_PREFERENCES_DAYOFWEEK, ""), dayOfWeek)) {
            editor.putInt(APP_PREFERENCES_SCORE,0);
            editor.putString(APP_PREFERENCES_DAYOFWEEK,dayOfWeek);
            editor.apply();
            System.out.println("------" +mSettings.getString(APP_PREFERENCES_DAYOFWEEK,""));
            System.out.println(dayOfWeek);
        }
        




        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 3000);

    }
}
