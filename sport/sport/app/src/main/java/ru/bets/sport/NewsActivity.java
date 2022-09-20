package ru.bets.sport;

import static ru.bets.sport.MainActivity.APP_PREFERENCES;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_BACKGROUND;
import static ru.bets.sport.MainActivity.APP_PREFERENCES_TEXTCOLOR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class NewsActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String myBg;
    LinearLayout linearLayout;
    ArrayList<NewsState> states;
    String url = "http://84.38.181.162/api/news.json";
    JSONArray arr = null;
    JSONObject jsonObject;
    TextView countryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        countryView = findViewById(R.id.countryView);
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
    public void setInitialData(String title, String date, String text, String image){

        states.add(new NewsState (title,date,text,image));


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

                NewsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            states = new ArrayList<NewsState>();
                            arr = new JSONArray(myResponse);
                            jsonObject = arr.getJSONObject(1);
                            System.out.println("---------------------------------------------------");
                            System.out.println(arr.getJSONObject(0));
                            for (int i = 0 ; i < arr.length() ; i++) {
                                jsonObject = new JSONObject(arr.get(i).toString());
                                System.out.println(jsonObject.optString("tittle"));
                                setInitialData(jsonObject.optString("tittle"),
                                        jsonObject.optString("date"),
                                        jsonObject.optString("text"),
                                        jsonObject.optString("img"));
                                System.out.println("повезло----------------------------");


                            }
                            RecyclerView recyclerView = findViewById(R.id.list);
                            // создаем адаптер
                            NewsStateAdapter adapter = new NewsStateAdapter(NewsActivity.this, states);
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