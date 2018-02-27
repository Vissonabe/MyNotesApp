package com.example.viswanathankp.mynotes.DB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String title;
    private String content;
    @TypeConverters(DateConverter.class)
    private Date createdDate;
    @Nullable
    private String imagePath;

    public Note() {}

    public Note(String titText, String conText, Date date, @Nullable String path){
        setTitle(titText);
        setContent(conText);
        setCreatedDate(date);
        setImagePath(path);
    }

    void setId(int id) { this.id = id; }

    void setTitle(String title){
        this.title = title;
    }

    void setContent(String content){
        this.content = content;
    }

    void setCreatedDate(Date date){
        this.createdDate = date;
    }

    void setImagePath(@Nullable String path){
        this.imagePath = path;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
