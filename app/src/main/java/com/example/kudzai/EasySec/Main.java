package com.example.kudzai.EasySec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        prefs = getSharedPreferences("com.example.kudzai.app21", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).commit();

            Intent intent = new Intent(".WelcomeScreen");
            startActivity(intent);






            // using the following line to edit/commit prefs

        }else{
            Intent intent = new Intent(".MainActivity");

            startActivity(intent);
        }
    }
}
