package com.example.viswanathankp.mynotes;

import android.app.Application;

import com.example.viswanathankp.mynotes.Dagger.DaggerMyComponent;
import com.example.viswanathankp.mynotes.Dagger.MyComponent;
import com.example.viswanathankp.mynotes.Dagger.MyModule;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class MyApplication extends Application {

    private MyComponent mMyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyComponent = createMyComponent();
    }

    public MyComponent getMyComponent() {
        return mMyComponent;
    }

    private MyComponent createMyComponent() {
        return DaggerMyComponent
                .builder()
                .application(this)
                .build();
    }

}
