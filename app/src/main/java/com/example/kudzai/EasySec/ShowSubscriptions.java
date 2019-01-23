package com.example.kudzai.EasySec;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowSubscriptions extends AppCompatActivity {
    Db_Operations myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_subscriptions);
        myDb = new Db_Operations(this);
        ViewSubcriptionDetails();
    }

    public void ViewSubcriptionDetails(){

        Cursor res = myDb.getAllData("Subscriptions");
        if (res.getCount() == 0) {
            // show message
            showMessage("Error", "Nothing found");

        } else {
            while (res.moveToNext()) {
                ArrayList<String> data0 = new ArrayList<String>();

                final ArrayList<String> data = new ArrayList<String>();
                final ArrayList<String> data1 = new ArrayList<String>();
                final ArrayList<String> data2 = new ArrayList<String>();
                final ArrayList<String> data3 = new ArrayList<String>();
                final ArrayList<String> data4 = new ArrayList<String>();
                final ArrayList<String> data5 = new ArrayList<String>();
                final ArrayList<String> data6 = new ArrayList<String>();




                TableLayout table;
                table = (TableLayout) findViewById(R.id.mytable1);


                data0.add(" "+res.getString(0));
                data.add("  "+res.getString(1));
                data1.add("  " + res.getString(2));
                data2.add("  " + res.getString(3));
                data3.add("  " + res.getString(4));
                data4.add("  " + res.getString(5));
                data5.add("  " + res.getString(6));
                data6.add("  " + res.getString(7));



                for (int i = 0; i < data.size(); i++) {
                    TableRow row = new TableRow(this);
                    final String id = data0.get(i);
                    final String date = data.get(i);
                    final String full_name = data1.get(i);
                    final String District = data2.get(i);
                    final String Amount = data3.get(i);
                    final String Purpose = data4.get(i);
                    final String Paymode = data5.get(i);
                    final String Reference = data6.get(i);


                    TextView tv0 = new TextView(this);
                    tv0.setText(id);
                    TextView tv1 = new TextView(this);
                    tv1.setText(date);
                    TextView tv2 = new TextView(this);
                    tv2.setText("   "+full_name);
                    TextView tv3 = new TextView(this);
                    tv3.setText(District);
                    TextView tv4 = new TextView(this);
                    tv4.setText(Amount);
                    TextView tv5 = new TextView(this);
                    tv5.setText(Purpose);

                    TextView tv6 = new TextView(this);
                    tv6.setText(Paymode);

                    TextView tv7 = new TextView(this);
                    tv7.setText(Reference);

                    Button btnDelete = new Button(this);
                    btnDelete.setText("Delete".toLowerCase());
                    //btnDelete.setHeight(2);
                    btnDelete.setMinHeight(0);
                    btnDelete.setHeight(5);
                    btnDelete.setMinHeight(8);
                    btnDelete.setTextColor(Color.parseColor("#D30B0B"));
                    btnDelete.setBackgroundColor(Color.parseColor("#00000000"));


                    Button btnView = new Button(this);
                    btnView.setText("Details".toLowerCase());
                    btnView.setMinHeight(0);
                    btnView.setHeight(5);
                    btnView.setMinHeight(8);
                    btnView.setTextColor(Color.parseColor("#62388E"));
                    btnView.setBackgroundColor(Color.parseColor("#00000000"));





                    btnDelete.setOnClickListener(
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    AlertDialog.Builder alertWrong = new AlertDialog.Builder(ShowSubscriptions.this);

                                    alertWrong.setMessage("delete data?").setCancelable(false)
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Cursor res = myDb.getAllData("Subscriptions");
                                                    Integer deletedRows = myDb.deleteData("Subscriptions" , id);
                                                    if(deletedRows > 0) {

                                                        Toast.makeText(ShowSubscriptions.this, "Data Deleted", Toast.LENGTH_LONG).show();
                                                        dialog.cancel();

                                                    }
                                                    else {
                                                        Toast.makeText(ShowSubscriptions.this, "Data not Deleted", Toast.LENGTH_LONG).show();

                                                    }

                                                }
                                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();

                                        }
                                    });
                                    AlertDialog alert = alertWrong.create();
                                    alert.setTitle("Warning");
                                    alert.show();

                                }
                            });


                    btnView.setOnClickListener(
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    StringBuffer buffer = new StringBuffer();



                                        buffer.append("Date             :"+date+"\n");
                                        buffer.append("Full Name   :" + full_name +"\n");
                                        buffer.append("District        :" + District +"\n");
                                        buffer.append("Amount       :" + Amount +"\n");
                                        buffer.append("Purpose      :" + Purpose +"\n");
                                        buffer.append("Paymode    :" + Paymode +"\n");
                                        buffer.append("Reference   :" + Reference +"\n\n");


                                    // Show all data
                                    showMessage("Subscriptions",buffer.toString());

                                }
                            });

                    //row.addView(tv1);
                    row.addView(tv2);
                   // row.addView(tv3);
                    row.addView(tv4);
                    //row.addView(tv5);
                    //row.addView(tv6);
                    //row.addView(tv7);

                    row.addView(btnDelete);
                    row.addView(btnView);

                    table.addView(row);
                }


            }


        }
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

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
}
