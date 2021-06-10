package com.example.myapplication.InformationSave;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.DB.App;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class KeyStore {
    public static final String PINCODE = "pin";
    public static final String SALT = "salt";

    private SharedPreferences sharedPreferences;
    private MessageDigest messageDigest;

    public KeyStore() {
        sharedPreferences = App.getInstance().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String encript(String pin, String salt) {
        pin += salt;
        byte[] data1 = pin.getBytes(StandardCharsets.UTF_8);
        byte[] digest = messageDigest.digest(data1);
        return new String(digest, StandardCharsets.UTF_8);
    }

    public boolean hasPin() {
        String savedpin = sharedPreferences.getString(PINCODE, "");
        return !(savedpin.equals(""));
    }

    public boolean checkPin(String pin) {
        String savedPin = sharedPreferences.getString(PINCODE, "");
        String savedSalt = sharedPreferences.getString(SALT, "somestaticsalt");
        pin = encript(pin, savedSalt);
        return pin.equals(savedPin);
    }

    private String generateSalt() {
        return Calendar.getInstance().getTime().toString();
    }

    public void save(String pin) {
        SharedPreferences.Editor Editor = sharedPreferences.edit();
        String salt = generateSalt();
        pin = encript(pin, salt);
        Editor.putString(PINCODE, pin);
        Editor.putString(SALT, salt);
        Editor.apply();
    }
}
