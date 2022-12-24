package com.example.hw2orielmalik322985441;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySPV3.init(this);



    }
}
