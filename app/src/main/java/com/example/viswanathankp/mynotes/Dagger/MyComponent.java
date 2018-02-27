package com.example.viswanathankp.mynotes.Dagger;

import android.app.Application;

import com.example.viswanathankp.mynotes.MyApplication;
import com.example.viswanathankp.mynotes.View.MainActivity;
import com.example.viswanathankp.mynotes.ViewModels.AddNoteViewModel;
import com.example.viswanathankp.mynotes.ViewModels.BaseViewModel;
import com.example.viswanathankp.mynotes.ViewModels.NoteDetailViewModel;
import com.example.viswanathankp.mynotes.ViewModels.NotesListViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MyComponent build();
    }

    //void inject(BaseViewModel baseViewModel);
    void inject(NotesListViewModel exampleViewModel);
    void inject(AddNoteViewModel exampleViewModel);
    void inject(NoteDetailViewModel exampleViewModel);
}
