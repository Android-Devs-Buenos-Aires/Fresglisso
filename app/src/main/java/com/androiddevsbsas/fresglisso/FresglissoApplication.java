package com.androiddevsbsas.fresglisso;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class FresglissoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
