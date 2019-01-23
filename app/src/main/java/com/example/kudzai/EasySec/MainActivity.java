package com.example.kudzai.EasySec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;


public class MainActivity extends AppCompatActivity {
    Db_Operations myDb;

    private static Button  buttonLogin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new Db_Operations(this);



            OnclickButtonListener();



    }
    @Override
    public void onBackPressed() {

                        MainActivity.super.onBackPressed();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public void OnclickButtonListener(){


        buttonLogin = (Button)findViewById(R.id.button);
        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {





                        EditText edemail = (EditText)findViewById(R.id.editText);
                        EditText edpass = (EditText)findViewById(R.id.editTextPassword);
                        TextView txtView = (TextView)findViewById(R.id.textViewLoginError);

                        Cursor res = myDb.getLoginData("Users" , edemail.getText().toString(), edpass.getText().toString());

                        if(res.getCount() == 0) {


                            AlertDialog.Builder alertWrong = new AlertDialog.Builder(MainActivity.this);

                            alertWrong.setMessage("Invalid Email or Pin").setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = alertWrong.create();
                            alert.setTitle("Warning");
                            alert.show();
                        }
                        else
                        {
                            Cursor result = myDb.IsAdmin("Users" , edemail.getText().toString(), 1);

                                if(result.getCount() > 0) {
                                    edemail.setText("");
                                    edpass.setText("");

                                    Intent intent = new Intent(".AdminPortal");


                                }
                                else{

                                    edemail.setText("");
                                    edpass.setText("");

                                    Intent intent = new Intent(".initialSystemView");
                                    startActivity(intent);
                                }


                        }


                    }
                }
        );

    }

    public void AdminFromSettings(final Context context){


        buttonLogin = (Button)findViewById(R.id.button);
        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {





                        EditText edemail = (EditText)findViewById(R.id.editText);
                        EditText edpass = (EditText)findViewById(R.id.editTextPassword);
                        TextView txtView = (TextView)findViewById(R.id.textViewLoginError);

                        Cursor res = myDb.getLoginData("Users" , edemail.getText().toString(), edpass.getText().toString());

                        if(res.getCount() == 0) {


                            AlertDialog.Builder alertWrong = new AlertDialog.Builder(context);

                            alertWrong.setMessage("Invalid Email or Pin").setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = alertWrong.create();
                            alert.setTitle("Warning");
                            alert.show();
                        }
                        else
                        {
                            Cursor result = myDb.IsAdmin("Users" , edemail.getText().toString(), 1);

                            if(result.getCount() > 0) {
                                edemail.setText("");
                                edpass.setText("");

                                Intent intent = new Intent(".AdminPortal");


                            }
                            else{

                                AlertDialog.Builder alertWrong = new AlertDialog.Builder(context);

                                alertWrong.setMessage("This account does not have admin rights").setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = alertWrong.create();
                                alert.setTitle("Warning");
                                alert.show();


                            }


                        }


                    }
                }
        );

    }


}
