package com.example.viswanathankp.mynotes.Dagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.viswanathankp.mynotes.DB.AppDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.viswanathankp.mynotes.DB.AppDatabase.DB_NAME;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

@Module
public class MyModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Application context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .build();
    }

}
