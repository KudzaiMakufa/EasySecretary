package com.example.kudzai.app21;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class adminFromSettings extends AppCompatActivity {

    private static Button buttonLogin;
    private static DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_from_settings);


            MainActivity main = new MainActivity();
            main.AdminFromSettings(adminFromSettings.this);
    }


}
