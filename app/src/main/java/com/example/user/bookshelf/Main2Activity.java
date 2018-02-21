package com.example.user.bookshelf;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import static android.net.wifi.WifiConfiguration.Status.strings;
import static android.view.KeyCharacterMap.load;

public class Main2Activity extends AppCompatActivity {
    PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromFile(new File(getIntent().getStringExtra("FILE"))).load();
        Log.e("App"," "+  "");

    }
}
