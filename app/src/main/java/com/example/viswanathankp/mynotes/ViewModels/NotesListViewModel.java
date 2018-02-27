package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.viswanathankp.mynotes.DB.AppDatabase;
import com.example.viswanathankp.mynotes.DB.Note;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class NotesListViewModel extends AndroidViewModel {

    private LiveData<List<Note>> notesList;

    private AppDatabase appDatabase;

    public NotesListViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        notesList = appDatabase.noteModel().getAllNotes();
    }

    public LiveData<List<Note>> getNotesList() {
        return notesList;
    }

    public List<Note> getSortedListByTitle(List<Note> list){
        Collections.sort(list, TitleComparator);
        return list;
    }

    public List<Note> getSortedListByDate(List<Note> list){
        Collections.sort(list, DateComparator);
        return list;
    }

    private static Comparator<Note> TitleComparator = new Comparator<Note>() {

        public int compare(Note s1, Note s2) {
            String title1 = s1.getTitle();
            String title2 = s2.getTitle();

            //ascending order
            return title1.compareTo(title2);
        }};

    static Comparator<Note> DateComparator = new Comparator<Note>() {

        public int compare(Note s1, Note s2) {
            //ascending order
            return s1.getCreatedDate().compareTo(s2.getCreatedDate());
        }};
}
