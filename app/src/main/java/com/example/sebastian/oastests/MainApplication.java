package com.example.sebastian.oastests;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by sebastian on 10/30/15.
 */
public class MainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Configuration configuration = new Configuration();
        configuration.locale = new Locale("es", "CL");
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
}
