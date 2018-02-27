package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.viswanathankp.mynotes.DB.AppDatabase;
import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.Utils.ImageSaver;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class AddNoteViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddNoteViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public String saveImageToFile(Bitmap bitmap){
        return ImageSaver.save(this.getApplication(), bitmap);
    }

    public void addNote(final Note note) {
        new addAsyncTask(appDatabase).execute(note);
    }

    private static class addAsyncTask extends AsyncTask<Note,  Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            db.noteModel().addNote(params[0]);
            return null;
        }

    }
}
