package ru.sport.trainingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrenerActivity extends AppCompatActivity {
    ImageButton btnSend;
    TextView textViewAnswer, textViewQuestion;
    EditText editTextQ;
    String question;
    JSONObject jsonObject;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trener);
        //----------------------------------------------
        btnSend = findViewById(R.id.butSend);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextQ = findViewById(R.id.editTextQuestion);

        //----------------------------------------------


    }

    public void onClick(View v) {
        question = editTextQ.getText().toString();
        id += 1;
        if (question.equals("") != true) {
            textViewQuestion.setText(question);
            try {
                run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }











    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();
        // Строим FormBody, передаем параметры для отправки
        FormBody formBody = new FormBody
                .Builder()
                .add("ask", question)
                .add("id", String.valueOf(id))
                .build();
        final Request request = new Request.Builder()
                .url("http://84.38.181.162/ios/ask.php ")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        textViewAnswer.setText("Ответ обрабатывается");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                postParameter();


            }
        });
    }
    public void postParameter() {
        OkHttpClient client = new OkHttpClient();
        // Строим FormBody, передаем параметры для отправки
        FormBody formBody = new FormBody
                .Builder()
                .add("id", String.valueOf(id))
                .build();
        final Request request = new Request.Builder()
                .url("http://84.38.181.162/ios/response.php ")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(responseStr);
                        try {
                            jsonObject = new JSONObject(responseStr);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textViewAnswer.setText(jsonObject.optString("response"));
                    }
                });
            }
        });
    }













}