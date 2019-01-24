package com.example.kudzai.EasySec;

    import android.content.Context;
    import android.database.Cursor;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.graphics.Paint;
    import android.graphics.pdf.PdfDocument;
    import android.os.Environment;
    import android.widget.Toast;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.util.ArrayList;

    import static java.lang.Integer.parseInt;

public class Pdf_Op_Method {

    Db_Operations myDb;

    public void createPdf(Context context) {

        myDb = new Db_Operations(context);


        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(500, 500, 2).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);

        //canvas.drawCircle(50, 50, 30, paint);

        document.finishPage(page);


        // Create Page 2
        pageInfo = new PdfDocument.PageInfo.Builder(500, 600, 1).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);


        //print alldata to pdf


        Cursor res = myDb.GetSumOfColumns("Subscriptions", "amount");
        Cursor result = myDb.GetSumOfColumns("Expenses", "amount");
        Cursor cashResult = myDb.GetPayModeTotals("Subscriptions", "paymode", "Cash");
        Cursor EcocashResult = myDb.GetPayModeTotals("Subscriptions", "paymode", "EcoCash");
        Cursor BankcashResult = myDb.GetPayModeTotals("Subscriptions", "paymode", "Bank");

        if (res.moveToFirst() && result.moveToFirst() && cashResult.moveToFirst() && EcocashResult.moveToFirst()
                && BankcashResult.moveToFirst()) {

            String alldata = res.getString(0);
            String allexpe = result.getString(0);
            String cashres = cashResult.getString(0);
            String ecores = EcocashResult.getString(0);
            String bankres = BankcashResult.getString(0);

            int income = 0;
            try {

                income = parseInt(alldata) - parseInt(allexpe);
            } catch (Exception e) {
                income = 0;
            }

            canvas.drawText("Income and Expenditure Statement for the period from ?? to  ??", 100, 160, paint);
            canvas.drawText("Income Received :                                                        ", 30, 210, paint);
            canvas.drawText("                Cash   :                               ' " + cashres + "' ", 30, 230, paint);
            canvas.drawText("                Ecocash:                              ' " + ecores + "'   ", 30, 250, paint);
            canvas.drawText("                Bank   :                               ' " + bankres + "' ", 30, 270, paint);
            canvas.drawText("                Total  :                               ' " + alldata + " '", 30, 290, paint);
            canvas.drawText("Less Expenses   :                                                     ", 30, 310, paint);
            canvas.drawText("                Expense           '( " + allexpe + ")'               ", 30, 330, paint);
            canvas.drawText("Net Income :                                                     ", 30, 350, paint);
            canvas.drawText("           :    Net                                     ' " + income + " '", 30, 370, paint);
            canvas.drawText("Compiled By:   Secretary                    _______________              ", 30, 460, paint);
            canvas.drawText("Date       :                                                           ", 30, 490, paint);

        }


        // finish the page
        document.finishPage(page);

        // write the document content
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "EasySecretary/Income Expenditure/");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }
        //appending cuurent timestamp to my generated file

        Date_Operations dateOp = new Date_Operations();
        String currenttime = dateOp.GetCurrentTimeAndDate();


        //String targetPdf = "/sdcard/EasySecretary/Income and Expenditure '"+currenttime+"'.pdf";
        String targetPdf = "/sdcard/EasySecretary/Income Expenditure/Income and Expenditure "+currenttime+".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context, "Pdf stored in \n " + targetPdf, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

    }


    public void printallSubOrExp(Context context , String tablename) {
        myDb = new Db_Operations(context);


        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(500, 500, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);

        Cursor res = myDb.getAllData(tablename);
        int y = 30;

        while (res.moveToNext()) {
            y += 10;

            ArrayList<String> data0 = new ArrayList<String>();
            final ArrayList<String> data = new ArrayList<String>();
            final ArrayList<String> data1 = new ArrayList<String>();
            final ArrayList<String> data2 = new ArrayList<String>();
            final ArrayList<String> data3 = new ArrayList<String>();
            final ArrayList<String> data4 = new ArrayList<String>();
            final ArrayList<String> data5 = new ArrayList<String>();
            final ArrayList<String> data6 = new ArrayList<String>();

            data0.add(" " + res.getString(0));
            data.add("  " + res.getString(1));
            data1.add("  " + res.getString(2));
            data2.add("  " + res.getString(3));
            data3.add("  " + res.getString(4));
            data4.add("  " + res.getString(5));
            data5.add("  " + res.getString(6));
            data6.add("  " + res.getString(7));


            for (int i = 0; i < data.size(); i++) {

                final String id = data0.get(i);
                final String date = data.get(i);
                final String full_name = data1.get(i);
                final String District = data2.get(i);
                final String Amount = data3.get(i);
                final String Purpose = data4.get(i);
                final String Paymode = data5.get(i);
                final String Reference = data6.get(i);


                canvas.drawText("'" + id + "'   '" + date + "'     '" + full_name + "'   '" + District + "'  '" + Amount + "'  '" + Purpose + "'  '" + Paymode + "'  '" + Reference + "'  ", 1, y, paint);

            }


        }
        // finish the page
        document.finishPage(page);

        // write the document content
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "EasySecretary/"+tablename+"");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }
        //appending cuurent timestamp to my generated file

        Date_Operations dateOp = new Date_Operations();
        String currenttime = dateOp.GetCurrentTimeAndDate();


        //String targetPdf = "/sdcard/EasySecretary/Income and Expenditure '"+currenttime+"'.pdf";
        String targetPdf = "/sdcard/EasySecretary/"+tablename+"/"+tablename+""+currenttime+".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context, "Pdf stored in \n " + targetPdf, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }



}
