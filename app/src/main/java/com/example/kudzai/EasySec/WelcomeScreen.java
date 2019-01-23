package com.example.kudzai.EasySec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
    Db_Operations myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        myDb = new Db_Operations(this);
        AddData();
    }
    public  void AddData() {
        myDb.insertData("Users","Administrator",
                "admin@admin.com",
                "pass123" , 1);

       Button Continue = (Button) findViewById(R.id.btncontinue);
        final EditText email = (EditText)findViewById(R.id.txtemail);
        final EditText password = (EditText)findViewById(R.id.txtpassword);

        Continue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData("Users","Account1",
                                email.getText().toString(),
                                password.getText().toString() , 0);


                        if(isInserted == true) {

                            //code to restart app





                           Toast.makeText(WelcomeScreen.this, "Account Created", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(".MainActivity");
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(WelcomeScreen.this, "Failed to create account", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
