package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.viswanathankp.mynotes.DB.AppDatabase;
import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.Model.NoteItem;
import com.example.viswanathankp.mynotes.MyApplication;
import com.example.viswanathankp.mynotes.Utils.Utils;

import javax.inject.Inject;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class NoteDetailViewModel extends BaseViewModel {

    @Inject
    public AppDatabase appDatabase;

    public NoteDetailViewModel(@NonNull Application application) {
        super(application);

        ((MyApplication) application)
                .getMyComponent()
                .inject(this);
    }

    public void updateNote(NoteItem note) {
        new updateAsyncTask(appDatabase).execute(Utils.getNote(note));
    }

    public void deleteNote(int id) {
        new deleteAsyncTask(appDatabase).execute(id);
    }

    private static class updateAsyncTask extends AsyncTask<Note,  Void, Void> {

        private AppDatabase db;

        updateAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            db.noteModel().update(params[0]);
            return null;
        }

    }

    private static class deleteAsyncTask extends AsyncTask<Integer,  Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            db.noteModel().deleteById(params[0]);
            return null;
        }

    }
}
