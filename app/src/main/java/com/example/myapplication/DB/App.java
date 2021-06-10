package com.example.myapplication.DB;

import android.app.Application;

import androidx.room.Room;

import com.example.myapplication.InformationSave.KeyStore;

public class App extends Application {

    public static App instance;
    private static KeyStore keystore;

    private AppDatabase database;

    public static KeyStore getKeystore() {
        return keystore;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        keystore = new KeyStore();

        database = Room.databaseBuilder(this, AppDatabase.class, "database8")
                .allowMainThreadQueries()
                .build();
    }



    public static App getInstance() {
        return instance;
    }

    public  AppDatabase getDatabase() {
        return database;
    }
}