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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StatsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    ArrayList<StatsState> states;
    JSONArray arr = null;
    JSONObject jsonObject;
    String lastrow = "Отборы з.и.";
    TextView header;
    Button btnAttack;
    Button btnSheild;
    TextView lastrowhead;
    public String url= "http://84.38.181.162/api/data_statistic_defense.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        header = findViewById(R.id.headView);
        header.setPaintFlags(header.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        lastrowhead = findViewById(R.id.textView20);
        btnAttack = findViewById(R.id.butAttack);
        btnSheild = findViewById(R.id.butShield);
        btnAttack.setBackgroundResource(R.drawable.non_active_button);
        btnSheild.setBackgroundResource(R.drawable.mybutton);
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        header.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btnSheild.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btnAttack.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butShield:
                url = "http://84.38.181.162/api/data_statistic_defense.json";
                lastrow = "Отборы з.и.";
                lastrowhead.setText(lastrow);
                btnAttack.setBackgroundResource(R.drawable.non_active_button);
                btnSheild.setBackgroundResource(R.drawable.mybutton);

                try {
                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.butAttack:
                url = "http://84.38.181.162/api/data_statistic_attack.json";
                lastrow = "Удары ВСтв з.и.";
                lastrowhead.setText(lastrow);
                btnAttack.setBackgroundResource(R.drawable.mybutton);
                btnSheild.setBackgroundResource(R.drawable.non_active_button);

                try {
                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public void setInitialData(String commmand, String tour, String hit, String pick){

        states.add(new StatsState (commmand,tour,hit,pick));


    }




    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                StatsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            states = new ArrayList<StatsState>();
                            arr = new JSONArray(myResponse);
                            jsonObject = arr.getJSONObject(1);
                            System.out.println("---------------------------------------------------");
                            System.out.println(arr.getJSONObject(0));
                            for (int i = 0 ; i < arr.length() ; i++) {
                                jsonObject = new JSONObject(arr.get(i).toString());
                                System.out.println(jsonObject.optString("Команда"));
                                setInitialData(jsonObject.optString("Команда"),
                                        jsonObject.optString("Турнир"),
                                        jsonObject.optString("Удары з.и."),
                                        jsonObject.optString(lastrow));
                                System.out.println("повезло----------------------------");


                            }
                            RecyclerView recyclerView = findViewById(R.id.list);
                            // создаем адаптер
                            StatsStateAdapter adapter = new StatsStateAdapter(StatsActivity.this, states);
                            // устанавливаем для списка адаптер
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }




                    }
                });

            }
        });
    }
}