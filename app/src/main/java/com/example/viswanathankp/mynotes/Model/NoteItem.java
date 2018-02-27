package com.example.viswanathankp.mynotes.Model;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

@Parcel
public class NoteItem {

    public int id;
    public String title;
    public String content;
    public Date date;
    public String imagePath;

    public NoteItem() {
    }

    public NoteItem(int id, String title, String content, Date date, String path) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.imagePath = path;
    }
}
