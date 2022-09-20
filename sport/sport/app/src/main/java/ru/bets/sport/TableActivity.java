package ru.bets.sport;

import static ru.bets.sport.MainActivity.APP_PREFERENCES;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_BACKGROUND;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_TEXTCOLOR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    ArrayList<State> states = new ArrayList<State>();
    TextView countryView;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        Bundle arguments = getIntent().getExtras();
        countryView = findViewById(R.id.countryView);
        TextView countryView = findViewById(R.id.countryView);
        countryView.setPaintFlags(countryView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        countryView.setText(arguments.get("country").toString());
        try {
            jsonObject = new JSONObject(arguments.get("name").toString());
            System.out.println(jsonObject);
            for (int i = 1 ; i < jsonObject.length() +1 ; i++) {
                setInitialData(String.valueOf(i),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("Команда"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("Игры"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("В"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("Н"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("П"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("Мячи"),
                        jsonObject.getJSONObject(String.valueOf(i)).optString("Очки"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.list);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(this, states);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
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
        countryView.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
    }
    public void setInitialData(String namber,String command, String i, String v, String n, String p, String balls, String score){

        states.add(new State (namber, command, i, v, n, p, balls, score));


    }
}