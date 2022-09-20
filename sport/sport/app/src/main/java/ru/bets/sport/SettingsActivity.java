package ru.bets.sport;

import static ru.bets.sport.MainActivity.APP_PREFERENCES_BACKGROUND;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_TEXTCOLOR;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    int i;
    SharedPreferences.Editor editor;
    boolean change = false;
    Switch aSwitch1;
    Switch aSwitch2;
    Button btn;
    TextView textView;
    TextView textView2;
    public static final String APP_PREFERENCES = "mysettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn = findViewById(R.id.butBackground);
        textView = findViewById(R.id.textView6);
        textView2 = findViewById(R.id.textView);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        aSwitch1 = findViewById(R.id.switch2);
        aSwitch2 = findViewById(R.id.switch3);
        linearLayout = findViewById(R.id.linear);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if(mSettings.contains(APP_PREFERENCES_BACKGROUND)) {
            myBg = mSettings.getString(APP_PREFERENCES_BACKGROUND, "");
        }
        int resID = getResources().getIdentifier(myBg , "drawable", getPackageName());
        linearLayout.setBackgroundResource(resID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        aSwitch2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        aSwitch1.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        textView.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        textView2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        System.out.println(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        if (mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0) == 0xff000000) {
            aSwitch2.setChecked(true);
            change = !change;
        }
    }

    public void onCLick(View v) {
        editor = mSettings.edit();
        switch (v.getId()){
            case R.id.butBackground:
                System.out.println("sds");
                i = i + 1;
                if (i > 3) {
                    i = 1;
                }

                editor.putString(APP_PREFERENCES_BACKGROUND, "background_" + i);
                editor.apply();

                if(mSettings.contains(APP_PREFERENCES_BACKGROUND)) {
                    myBg = mSettings.getString(APP_PREFERENCES_BACKGROUND, "");
                }
                int resID = getResources().getIdentifier(myBg , "drawable", getPackageName());
                linearLayout.setBackgroundResource(resID);

                break;
            case R.id.switch3:

                if (change) {
                    editor.putInt(APP_PREFERENCES_TEXTCOLOR, 0xffffffff);
                    editor.apply();
                }else {

                    editor.putInt(APP_PREFERENCES_TEXTCOLOR, 0xff000000);
                    editor.apply();
                }
                change = !change;

                aSwitch2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                aSwitch1.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                textView.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                textView2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                btn.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                System.out.println(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
                break;
        }


        }
    }
