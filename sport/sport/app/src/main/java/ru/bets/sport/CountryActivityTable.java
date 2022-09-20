package ru.bets.sport;

import static ru.bets.sport.MainActivity.APP_PREFERENCES;
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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CountryActivityTable extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    TextView txtString;
    public String url= "http://84.38.181.162/api/data_tournament_tables.json";
    JSONArray arr = null;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_table);
        txtString= (TextView)findViewById(R.id.textView5);
        btn1 = findViewById(R.id.butTables);
        btn2 = findViewById(R.id.butNews);
        btn3 = findViewById(R.id.butStats);
        btn4 = findViewById(R.id.butSettings);
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
        txtString.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn1.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn2.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn3.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
        btn4.setTextColor(mSettings.getInt(APP_PREFERENCES_TEXTCOLOR, 0));
    }
    public void onClick(View v) {
        Intent intent = new Intent(CountryActivityTable.this,TableActivity.class);
        switch (v.getId()) {
            case R.id.butTables:
                System.out.println(arr);
                intent.putExtra("country","РОССИЯ");
                try {

                    intent.putExtra("name",arr.getJSONObject(1).optString("data"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;
            case R.id.butNews:
                System.out.println(arr);
                intent.putExtra("country","АНГЛИЯ");
                try {

                    intent.putExtra("name",arr.getJSONObject(0).optString("data"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;
            case R.id.butStats:
                System.out.println(arr);
                intent.putExtra("country","ГЕРМАНИЯ");
                try {

                    intent.putExtra("name",arr.getJSONObject(3).optString("data"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;
            case R.id.butSettings:
                System.out.println(arr);
                intent.putExtra("country","ИСПАНИЯ");
                try {

                    intent.putExtra("name",arr.getJSONObject(2).optString("data"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;
        }
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

                CountryActivityTable.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            arr = new JSONArray(myResponse);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }




                    }
                });

            }
        });
    }

}