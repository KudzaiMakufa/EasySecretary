package com.example.kudzai.app21;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrintTesting extends AppCompatActivity {

    private static Button print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_testing);

        print = (Button)findViewById(R.id.button2);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printDocument(null);
            }
        });
    }

    public void printDocument(View view)
    {


        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = this.getString(R.string.app_name) +
                " Document";

        printManager.print(jobName, new MyPrintDocumentAdapter(this),
                null);
    }
    public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
        Context context;
        private int pageHeight;
        private int pageWidth;
        public PdfDocument myPdfDocument;
        public int totalpages = 1;


        public MyPrintDocumentAdapter(Context context) {
            this.context = context;
        }

        @Override
        public void onLayout(PrintAttributes oldAttributes,
                             PrintAttributes newAttributes,
                             CancellationSignal cancellationSignal,
                             LayoutResultCallback callback,
                             Bundle metadata) {


            myPdfDocument = new PrintedPdfDocument(context, newAttributes);

            pageHeight =
                    newAttributes.getMediaSize().getHeightMils() / 1000 * 72;
            pageWidth =
                    newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

            if (cancellationSignal.isCanceled()) {
                callback.onLayoutCancelled();
                return;
            }

            if (totalpages > 0) {
                PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                        .Builder("print_output.pdf")
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(totalpages);

                PrintDocumentInfo info = builder.build();
                callback.onLayoutFinished(info, true);
            } else {
                callback.onLayoutFailed("Page count is zero.");
            }
        }


        @Override
        public void onWrite(final PageRange[] pageRanges,
                            final ParcelFileDescriptor destination,
                            final CancellationSignal cancellationSignal,
                            final WriteResultCallback callback) {

            for (int i = 0; i < totalpages; i++) {
                if (pageInRange(pageRanges, i)) {
                    PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                            pageHeight, i).create();

                    PdfDocument.Page page =
                            myPdfDocument.startPage(newPage);

                    if (cancellationSignal.isCanceled()) {
                        callback.onWriteCancelled();
                        myPdfDocument.close();
                        myPdfDocument = null;
                        return;
                    }
                    drawPage(page, i);
                    myPdfDocument.finishPage(page);

                }
            }

            saveFile("Promise",myPdfDocument);


            callback.onWriteFinished(pageRanges);

        }

        private boolean pageInRange(PageRange[] pageRanges, int page) {
            for (int i = 0; i < pageRanges.length; i++) {
                if ((page >= pageRanges[i].getStart()) &&
                        (page <= pageRanges[i].getEnd()))
                    return true;
            }
            return false;
        }

        private void drawPage(PdfDocument.Page page,
                              int pagenumber) {
            Canvas canvas = page.getCanvas();

            pagenumber++; //

            int titleBaseLine = 90;
            int leftMargin = 50;

        /*Drawable icon = getResources().getDrawable(R.drawable.pot);
        icon.setBounds(leftMargin - 40, 40, 60, 80);
        icon.getIntrinsicHeight();
        icon.getIntrinsicWidth();

        icon.draw(canvas);*/


            Paint paint = new Paint();
            paint.setColor(Color.BLACK);

            paint.setTextSize(18);
            //Typeface fontRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

            //paint.setTypeface(fontRegular);
            canvas.drawText("PDF SAMPLE", leftMargin, titleBaseLine, paint);
            paint.setTextSize(14);
            canvas.drawText("PDF LINE NO 1", leftMargin, titleBaseLine + 30, paint);

            paint.setTextSize(12);
            canvas.drawText("PDF LINE NO 1", leftMargin, titleBaseLine + 50, paint);

            paint.setTextSize(14);
            canvas.drawText("PDF LINE NO 1", leftMargin, titleBaseLine + 80, paint);

            paint.setTextSize(12);
            canvas.drawText("PDF LINE NO 1", leftMargin, titleBaseLine + 100, paint);


        }
    }

    public void saveFile( String fileName, PdfDocument document) {

        try {

            File mypath=new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"filename.pdf");
            document.writeTo(new FileOutputStream(mypath));

            document.close();

        }
        catch(Exception e){
        }
    }

}



