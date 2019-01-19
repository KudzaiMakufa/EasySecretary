package com.example.kudzai.app21;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static java.lang.Integer.parseInt;

public class initialSystemView extends AppCompatActivity {

    private static Button btnShowExp, btnShowSubs,btnbSub;
    private static TextView  nill ;
    private static EditText editText ,editTextexp,txtAmount ,txtDescription,Fullname , txtdistrict, txtamount , txtpurpose ,txtreference ,datefrom,dateto;
    private static DatabaseHelper myDb ;
    private static CheckBox cash , ecocash ,bank ;
    private static Button insert , viewexp ,CreatePdf,btnView,btnsettle;
    private static Spinner spnType,spnTyped ;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_system_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



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
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out  ?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        initialSystemView.super.onBackPressed();
                    }
                }).create().show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initial_system_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(".adminFromSettings");
            startActivity(intent);

        }
        else if(id == R.id.action_backup){
            final String SAMPLE_DB_NAME = "Zaoga.db";
            AlertDialog.Builder alertWrong = new AlertDialog.Builder(initialSystemView.this);

            alertWrong.setMessage("Backup data?").setCancelable(false)
                    .setPositiveButton("Backup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File sd = Environment.getExternalStorageDirectory();
                            File data = Environment.getDataDirectory();
                            FileChannel source=null;
                            FileChannel destination=null;
                            String currentDBPath = "/data/"+ "com.example.kudzai.app21" +"/databases/"+SAMPLE_DB_NAME;

                            File folder = new File(Environment.getExternalStorageDirectory() +
                                    File.separator + "EasySecretary");
                            boolean success = true;
                            if (!folder.exists()) {
                                success = folder.mkdirs();
                            }

                            String backupDBPath = "" +
                                    "/EasySecretary/SAMPLE_DB_NAME";
                            File currentDB = new File(data, currentDBPath);
                            File backupDB = new File(sd, backupDBPath);
                            try {
                                source = new FileInputStream(currentDB).getChannel();
                                destination = new FileOutputStream(backupDB).getChannel();
                                destination.transferFrom(source, 0, source.size());
                                source.close();
                                destination.close();
                                Toast.makeText(getApplicationContext(),"Your database has been exported",
                                        Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                e.printStackTrace();
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
        else{

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){

                myDb = new DatabaseHelper(getActivity());



                View rootView = inflater.inflate(R.layout.fragment_subscriptions, container, false);

                Fullname = (EditText)rootView.findViewById(R.id.edtxtFullname);
                txtdistrict = (EditText)rootView.findViewById(R.id.edtxtDistrict);
                txtamount = (EditText)rootView.findViewById(R.id.edtxtAmnt);
                txtpurpose = (EditText)rootView.findViewById(R.id.edtxtPurpose);
                txtreference = (EditText)rootView.findViewById(R.id.edtxtReference);
                cash = (CheckBox)rootView.findViewById(R.id.chckCash);
                ecocash = (CheckBox)rootView.findViewById(R.id.chckEcocash);
                bank    = (CheckBox)rootView.findViewById(R.id.chckBank);

                editText = (EditText)rootView.findViewById(R.id.edsubtxtDate);
                disableSoftInputFromAppearing(editText);
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //fuck bro ..look how instance of objects save your ass in typing .DO NOT REPEAT YOURSELF(DRY) lol
                        Date_Operations dateop = new Date_Operations();
                        dateop.appDate(editText,getActivity());

                    }
                });





                //on button click listenner for inserting into db

                btnbSub = (Button)rootView.findViewById(R.id.btnSubmit);
                btnbSub.setOnClickListener(

                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String paymode = null;

                                if(cash.isChecked() == true && (ecocash.isChecked() == false && bank.isChecked() == false)){
                                    paymode = "Cash";
                                    //Toast.makeText(createSubscription.this, "Cash", Toast.LENGTH_LONG).show();
                                }
                                else if(ecocash.isChecked() == true && (cash.isChecked() == false && bank.isChecked() == false)){
                                    paymode = "EcoCash";
                                    // Toast.makeText(createSubscription.this, "EcoCash", Toast.LENGTH_LONG).show();
                                }
                                else if(bank.isChecked() == true && (cash.isChecked() == false && ecocash.isChecked() == false)){

                                    paymode = "Bank";
                                    //Toast.makeText(createSubscription.this, "bank", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    paymode = null ;
                                }






                                if(paymode != null) {



                                    try{

                                        boolean isInserted = myDb.insertSubscription("Subscriptions",
                                                editText.getText().toString(),
                                                Fullname.getText().toString(),
                                                txtdistrict.getText().toString(),
                                                parseInt(txtamount.getText().toString()),
                                                txtpurpose.getText().toString(),
                                                paymode,
                                                txtreference.getText().toString());
                                        if (isInserted == true) {
                                            Toast.makeText(getActivity(), "Payment Recorded Inserted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Payment Not Recorded Inserted", Toast.LENGTH_SHORT).show();
                                        }
                                    }catch(Exception e){
                                        Toast.makeText(getActivity(), "Fill in the amount input fields", Toast.LENGTH_SHORT).show();
                                    }





                                }
                                else{
                                    Toast.makeText(getActivity(), "Tick a pay mode Option", Toast.LENGTH_SHORT).show();
                                }

                                // Intent intent = new Intent(".ShowSubscriptions");

                                //startActivity(intent);



                            }



                        }
                );


                btnShowSubs = (Button)rootView.findViewById(R.id.btnShow);
                btnShowSubs.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {




                                Intent intent = new Intent(".ShowSubscriptions");

                                startActivity(intent);



                            }
                        }
                );







                return rootView;
            }



            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2) {

                myDb = new DatabaseHelper(getActivity());

                View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);


                txtAmount = (EditText)rootView.findViewById(R.id.edtxtAmount);
                txtDescription  = (EditText)rootView.findViewById(R.id.edtxtDescription);

                editTextexp = (EditText) rootView.findViewById(R.id.edexptxtDate);
                disableSoftInputFromAppearing(editTextexp);
                editTextexp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //getting calender widget

                        Date_Operations dateop = new Date_Operations();
                        dateop.appDate(editTextexp,getActivity());



                    }
                });

                insert = (Button) rootView.findViewById(R.id.btnShowExpense);

                insert.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                        if(txtAmount.length() == 0){


                            Toast.makeText(getActivity() , "Fill all required fields" ,Toast.LENGTH_SHORT).show();


                        }
                        else{
                            boolean isInserted = myDb.insertExp("Expenses", editTextexp.getText().toString(), parseInt(txtAmount.getText().toString()), txtDescription.getText().toString());
                            if(isInserted == true)
                                Toast.makeText(getActivity(),"Expense Recorded",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getActivity(),"Expense Not Recorded",Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                viewexp = (Button) rootView.findViewById(R.id.btnviewExp);
                viewexp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".ShowExpenses");

                        startActivity(intent);
                    }
                });













                return rootView;
            }



            else{
                final View rootView = inflater.inflate(R.layout.fragment_management, container, false);




                /*



                 this is now the code for Management activity



                  */


                myDb = new DatabaseHelper(getActivity());
                spnType = (Spinner)rootView.findViewById(R.id.spinnerType);
                final TextView txtExpense = (TextView)rootView.findViewById(R.id.txtViewExp);
                final TextView txtSubscriptions = (TextView)rootView.findViewById(R.id.txtSub);
                final TextView txtIncome = (TextView)rootView.findViewById(R.id.txtNetIncome);
                final TextView txtcashres = (TextView)rootView.findViewById(R.id.txtCash);
                final TextView txtEcores = (TextView)rootView.findViewById(R.id.txtEcocash);
                final TextView txtBankres = (TextView)rootView.findViewById(R.id.txtBank);
                datefrom = (EditText)rootView.findViewById(R.id.edtxtdatefrom);
                disableSoftInputFromAppearing(datefrom);
                dateto = (EditText)rootView.findViewById(R.id.edtxtdateto);
                disableSoftInputFromAppearing(dateto);

                btnView = (Button)rootView.findViewById(R.id.btnviewdate);
                btnsettle = (Button)rootView.findViewById(R.id.btnsettle);




                String []  dropdowntype = new String[]{"None","Settle All","Settle by Date", "Subscriptions","Reconcile All","Reconcile by Date"};

// Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dropdowntype);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                spnType.setAdapter(adapter);



                spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String Spinnergettype = ((Spinner)rootView.findViewById(R.id.spinnerType)).getSelectedItem().toString();

                        switch (position) {

                            case 1:
                                if(Spinnergettype == "Settle All" ){


                                    AlertDialog.Builder alertWrong = new AlertDialog.Builder(getActivity());

                                    alertWrong.setMessage("delete all data?").setCancelable(false)
                                            .setPositiveButton("Settle", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Integer settleExpenses = myDb.SettleAccount("Expenses");
                                                    Integer settleSubscriptions = myDb.SettleAccount("Subscriptions");


                                                    if(settleExpenses > 0 && settleSubscriptions <= 0 ){
                                                        Toast.makeText(getActivity(),"Expenses Settled",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(settleExpenses <= 0 && settleSubscriptions > 0 ){
                                                        Toast.makeText(getActivity(),"Subscriptions Settled",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else if(settleExpenses <= 0 && settleSubscriptions <= 0 ){
                                                        Toast.makeText(getActivity(),"No data to delete",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else{
                                                        Toast.makeText(getActivity(),"Accounts settled",Toast.LENGTH_SHORT).show();
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


                                break;
                            case 2:
                                if(Spinnergettype == "Settle by Date"){
                                    btnView = (Button)rootView.findViewById(R.id.btnviewdate);
                                    EditText datefrom = (EditText)rootView.findViewById(R.id.edtxtdatefrom);

                                    EditText dateto = (EditText)rootView.findViewById(R.id.edtxtdateto);


                                    btnView.setText("Settle by date");

                                }
                                break;
                            case 3:
                                if(Spinnergettype == "Subscriptions") {

                                    Cursor res = myDb.GetSumOfColumns("Subscriptions","amount");

                                    if (res.moveToFirst()){

                                        String alldata = res.getString(0);
                                        if(alldata == "0"){
                                            txtSubscriptions.setText("0.00");

                                            txtExpense.setText("0.00");
                                            txtIncome.setText("0.00");
                                        }
                                        else {
                                            txtSubscriptions.setText(alldata);
                                            txtExpense.setText("0.00");
                                            txtIncome.setText("0.00");

                                        }




                                    }

                                }
                                break;
                            case 4:
                                if(Spinnergettype == "Reconcile All"){

                                    Cursor res = myDb.GetSumOfColumns("Subscriptions","amount");
                                    Cursor result = myDb.GetSumOfColumns("Expenses","amount");
                                    Cursor cashResult = myDb.GetPayModeTotals("Subscriptions","paymode","Cash");
                                    Cursor EcocashResult = myDb.GetPayModeTotals("Subscriptions","paymode","EcoCash");
                                    Cursor BankcashResult = myDb.GetPayModeTotals("Subscriptions","paymode","Bank");

                                    if (res.moveToFirst() && result.moveToFirst() && cashResult.moveToFirst() && EcocashResult.moveToFirst()
                                            && BankcashResult.moveToFirst()){

                                        String alldata = res.getString(0);
                                        String allexpe = result.getString(0);
                                        String cashres = cashResult.getString(0);
                                        String ecores = EcocashResult.getString(0);
                                        String bankres = BankcashResult.getString(0);

                                        int income = 0;
                                        try{

                                            income = parseInt(alldata) - parseInt(allexpe);
                                        }
                                        catch(Exception e){
                                            income = 0;
                                        }

                                        txtIncome.setText(Integer.toString(income));
                                        txtExpense.setText("("+allexpe+")");
                                        txtSubscriptions.setText(alldata);
                                        txtcashres.setText(cashres);
                                        txtEcores.setText(ecores);
                                        txtBankres.setText(bankres);




                                    }
                                }
                                break;
                            case 5:
                                if(Spinnergettype == "Reconcile by Date"){
                                    btnView = (Button)rootView.findViewById(R.id.btnviewdate);



                                    btnView.setText("Reconcile by date");

                                    /*

                                    Nothing here yet
                                    
                                     */

                                }
                                break;

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        // sometimes you need nothing here
                    }
                });

                /*
                 *
                 *
                 *
                 *
                 *
                 * here are the defined onclick actions
                 *
                 *
                 *
                 *
                 *
                 * */


                btnView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String from =  datefrom.getText().toString();
                        String to =  dateto.getText().toString();
                        Cursor res = myDb.GetFromTodata("Subscriptions",from,to);
                        Cursor result = myDb.GetFromTodata("Expenses",datefrom.getText().toString(),dateto.getText().toString());
                        Cursor cashResult = myDb.GetPayModeTotals("Subscriptions","paymode","Cash");
                        Cursor EcocashResult = myDb.GetPayModeTotals("Subscriptions","paymode","EcoCash");
                        Cursor BankcashResult = myDb.GetPayModeTotals("Subscriptions","paymode","Bank");

                        if (res.moveToFirst() && result.moveToFirst() && cashResult.moveToFirst() && EcocashResult.moveToFirst()
                                && BankcashResult.moveToFirst()){

                            String alldata = res.getString(0);
                            String allexpe = result.getString(0);
                            String cashres = cashResult.getString(0);
                            String ecores = EcocashResult.getString(0);
                            String bankres = BankcashResult.getString(0);

                            int income = 0;
                            try{

                                income = parseInt(alldata) - parseInt(allexpe);

                            }
                            catch(Exception e){
                                income = 0;
                            }

                            txtIncome.setText(Integer.toString(income));
                            txtExpense.setText("("+allexpe+")");
                            txtSubscriptions.setText(alldata);
                            txtcashres.setText(cashres);
                            txtEcores.setText(ecores);
                            txtBankres.setText(bankres);




                        }
                    }
                });




                CreatePdf  = (Button)rootView.findViewById(R.id.btnPrintPdf);
                CreatePdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(".PrintTesting");
                        Intent intent = new Intent(".MainListView");

                        startActivity(intent);

                    }
                });
                //Onclick listener for button view on total subscriptions

                Button ViewSubs = (Button)rootView.findViewById(R.id.btnviewsub);
                ViewSubs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".ShowSubscriptions");
                        startActivity(intent);
                    }
                });
                //Onclick listener for button view on total expenses

                Button ViewExp = (Button)rootView.findViewById(R.id.btnviewexp);
                ViewExp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".ShowExpenses");
                        startActivity(intent);
                    }
                });

                //from date onclick listener

                datefrom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        datefrom.setText("");

                        Date_Operations dateop = new Date_Operations();
                        dateop.appDate(datefrom , getActivity());
                    }
                });

                //to date onclick listener

                dateto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dateto.setText("");

                        Date_Operations dateop = new Date_Operations();
                        dateop.appDate(dateto , getActivity());

                    }
                });




                return rootView;
            }

        }
    }

    public static void disableSoftInputFromAppearing(EditText editText) {
        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }



            // you need to check how you can make add data static and getActivity on context

}
