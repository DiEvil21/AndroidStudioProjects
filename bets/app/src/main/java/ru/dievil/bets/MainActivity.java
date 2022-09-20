package ru.dievil.bets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("----------------------");
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



















    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://159.69.90.204/api/strateg/menu.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("----------------------");
                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(myResponse);
                        try {
                            jsonObject = new JSONObject(myResponse);
                            //jsonArray = new JSONArray(jsonObject.getJSONArray("меню"));

                            System.out.println(jsonObject.getJSONArray("меню").getString(0));
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getJSONArray("меню").getString(0));
                            for (int i = 0; jsonObject1.length() >= i ; i++) {
                                jsonObject1 = new JSONObject(jsonObject.getJSONArray("меню").getString(i));
                                System.out.println(jsonObject1.optString("btn"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });

            }
        });
    }







}