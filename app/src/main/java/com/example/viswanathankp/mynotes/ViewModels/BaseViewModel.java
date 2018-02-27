package com.example.viswanathankp.mynotes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.viswanathankp.mynotes.MyApplication;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class BaseViewModel extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
