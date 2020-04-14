package com.example.tabunganku_fix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.icu.util.RangeValueIterator;
import android.os.Bundle;
import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PdfDocument extends Activity {
    private static Font Font18bold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Font Font12 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLACK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_document);

        try {
            String filename = "Test"+".pdf";
            File defaultfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"{directorydocument}");
            File file = new File(defaultfile, filename);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            document.setPageSize(PageSize.A4);

            Chunk namaPaket = new Chunk("Nama Tabungan", Font12);
            Paragraph namaPaketParagraf = new Paragraph(namaPaket);
            namaPaketParagraf.setAlignment(Element.ALIGN_LEFT);
            document.add(namaPaketParagraf);

            document.close();

         } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
