package com.example.viswanathankp.mynotes.Utils;

import android.text.format.DateUtils;

import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.Model.NoteItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class Utils {

    public static NoteItem getNoteItem(Note note) {
        return new NoteItem(note.getId(), note.getTitle(), note.getContent(), note.getCreatedDate(), note.getImagePath());
    }

    public static Note getNote(NoteItem note) {
        Note n = new Note(note.title, note.content, note.date, note.imagePath);
        n.id = note.id;
        return n;
    }
}
