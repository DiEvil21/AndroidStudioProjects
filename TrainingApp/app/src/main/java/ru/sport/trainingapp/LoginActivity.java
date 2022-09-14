package ru.sport.trainingapp;

import androidx.appcompat.app.AppCompatActivity;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_NAME;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_HEIGHT;
import static ru.sport.trainingapp.LoadActivity.APP_PREFERENCES_WEIGHT;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    EditText editName, editHeight, editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //--------------------------------------------

        editName = findViewById(R.id.editName);
        editHeight = findViewById(R.id.editHeigth);
        editWeight = findViewById(R.id.editWeight);

        //--------------------------------------------
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSettings.edit();

    }
    public void onClick(View v) {
        editor.putString(APP_PREFERENCES_NAME, editName.getText().toString());
        editor.putString(APP_PREFERENCES_WEIGHT, editWeight.getText().toString());
        editor.putString(APP_PREFERENCES_HEIGHT, editHeight.getText().toString());
        editor.apply();
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}