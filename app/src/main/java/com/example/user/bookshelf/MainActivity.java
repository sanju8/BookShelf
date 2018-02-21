package com.example.user.bookshelf;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;

import com.tdscientist.shelfview.BookModel;
import com.tdscientist.shelfview.ShelfView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShelfView.BookClickListener {

    private ArrayList<BookModel> models;
    private boolean sentToSettings = false;
    SparseArray<File> books;

    private SharedPreferences permissionStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        permissionStatus = getSharedPreferences("permissionStatus",MODE_PRIVATE);
        ShelfView shelfView = (ShelfView) findViewById(R.id.shelfView);
        shelfView.setOnBookClicked(this);
        models = new ArrayList<>();
        books = new SparseArray<>();

//        models.add(new BookModel("http://eurodroid.com/pics/beginning_android_book.jpg", "1", "Beginning Android"));
//        models.add(new BookModel("beginning_android_book", "1", "Beginning Android"));
//        walkdir(new File(Environment.getExternalStorageDirectory(), "pdfs"));
        shelfView.loadData(models, ShelfView.BOOK_SOURCE_DRAWABLE_FOLDER);
//        shelfView.loadData(models, ShelfView.BOOK_SOURCE_URL);

    }

    @Override
    public void onBookClicked(int position, String bookId, String bookTitle) {
        // handle click events here
//        File mydir = getApplicationContext().getDir("pdfs   ", Context.MODE_PRIVATE); //Creating an internal dir;
//        if (!mydir.exists())
//        {
//            mydir.mkdirs();
//        }
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "pdfs");
//
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.e("App", "failed to create directory");
//            }
//        }



//        File file = new File(Environment.getExternalStorageDirectory(), "pdfs/beginning_android_book.pdf");

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("FILE", books.get(Integer.valueOf(bookId)).getAbsolutePath());
        startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(books.get(Integer.valueOf(bookId))), "application/pdf");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        startActivity(intent);

//        Toast.makeText(this, ""+ file.exists(), Toast.LENGTH_SHORT).show();
    }


    public void walkdir(File dir) {
        String pdfPattern = ".pdf";

        File[] listFile = dir.listFiles();
        int id = 1;
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    walkdir(listFile[i]);
                } else {
                    if (listFile[i].getName().endsWith(pdfPattern)){

                        if(listFile[i].getName().equals("beginning_android_book.pdf"))
                        {
                            books.put(id,listFile[i]);
                            models.add( new BookModel("beginning_android_book", String.valueOf(id++), listFile[i].getName()));

                        }
                        //Do what ever u want
//                        models.add(new BookModel("pdf_cover", String.valueOf(id++), listFile[i].getName()));
                        else {
                            books.put(id, listFile[i]);
                            models.add(new BookModel("pdf_cover", String.valueOf(id++), listFile[i].getName()));
                        }

//                        modelMap.put(listFile[i], new BookModel("pdf_cover", String.valueOf(id++), listFile[i].getName()));
                        Log.e("App", ""+id);
                    }
                }
            }
        }    }
private final int MY_PERMISSIONS_REQUEST = 0;
        private void checkForPermission()
        {
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant

                return;
            }
        }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkForPermission();
        walkdir(Environment.getExternalStorageDirectory());
    }
}

