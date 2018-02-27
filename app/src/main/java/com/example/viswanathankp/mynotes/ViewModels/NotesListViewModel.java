package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.viswanathankp.mynotes.DB.AppDatabase;
import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.MyApplication;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class NotesListViewModel extends BaseViewModel {

    private LiveData<List<Note>> notesList;

    @Inject
    public AppDatabase appDatabase;

    public NotesListViewModel(@NonNull Application application) {
        super(application);

        ((MyApplication) application)
                .getMyComponent()
                .inject(this);

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
