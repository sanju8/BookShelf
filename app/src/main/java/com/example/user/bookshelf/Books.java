package com.example.user.bookshelf;

import com.tdscientist.shelfview.BookModel;

import java.io.File;

/**
 * Created by USER on 2/2/2018.
 */

public class Books {
    File file;


    BookModel model;

    public Books(File file, BookModel model) {
        this.file = file;
        this.model = model;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BookModel getModel() {
        return model;
    }

    public void setModel(BookModel model) {
        this.model = model;
    }
}
