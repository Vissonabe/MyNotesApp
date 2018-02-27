package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.viswanathankp.mynotes.DB.AppDatabase;
import com.example.viswanathankp.mynotes.Model.NoteItem;
import com.example.viswanathankp.mynotes.Utils.Utils;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class NoteDetailViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public NoteDetailViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void updateNote(NoteItem note) {
        appDatabase.noteModel().update(Utils.getNote(note));
    }

    public void deleteNote(int id) {
        appDatabase.noteModel().deleteById(id);
    }
}
