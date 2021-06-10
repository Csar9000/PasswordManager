package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PasswordManagerDB3";
    public static final String TABLE_CONTACTS = "Category";
    public static final String TABLE_OFFICEPASS = "Alarm";
    public static final String TABLE_BICYCLEPASS = "Bicycle";
    public static final String TABLE_LAPTOPPASS = "Laptops";
    public static final String TABLE_BANKCARDS = "Cards";



    public static final String KEY_NAME = "name";
    public static final String DESCRIPTION = "description";

    public static final String KEY_NAMECAT_OFFICE = "nameCat";
    public static final String KEY_ID_Office = "id";
    public static final String OFFICE = "office";
    public static final String PASSWORD = "password";
    public static final String DESCRIPTIONOFFICE = "descriptionOffice";


    public static final String KEY_OWNERNAME = "ownerName";
    public static final String KEY_NAMECAT_BICYCLE = "nameCat";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DESCRIPTION = "description";


    public static final String KEY_LAPTOPOWNER_NAME = "laptopOwnerName";
    public static final String KEY_NAMECAT_LAPTOP = "nameCat";
    public static final String KEY_LAPTOPMODEL = "laptopModel";
    public static final String KEY_LAPTOPPASS = "laptopPass";
    public static final String KEY_DESCRIPTION_LAPTOP = "laptopDescription";


    public static final String KEY_CARDNUMBER = "cardNumber";
    public static final String KEY_NAMECAT_CARD = "nameCat";
    public static final String KEY_CARDPASSWORD = "password";









    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL("create table " + TABLE_CONTACTS +
                "(" + KEY_NAME + " text primary key," +
                DESCRIPTION + " text" + ")");

        String Office_Script = "CREATE TABLE If NOT EXISTS " + TABLE_OFFICEPASS + "(" +
                KEY_ID_Office + " INTEGER PRIMARY KEY, " +
                OFFICE + " TEXT NOT NULL, " + //perhaps make unique
                PASSWORD + " TEXT NOT NULL, " +
                DESCRIPTIONOFFICE +" TEXT NOT NULL,"+
                KEY_NAMECAT_OFFICE + " text not null," +
                "FOREIGN KEY("+ KEY_NAMECAT_OFFICE +")"+" REFERENCES "+TABLE_CONTACTS+"("+KEY_NAME+")"+
                ")";

        String Bicycle_Script = "CREATE TABLE If NOT EXISTS " + TABLE_BICYCLEPASS + "(" +
                KEY_OWNERNAME + " TEXT PRIMARY KEY, " +
                KEY_NAMECAT_BICYCLE + " TEXT NOT NULL, " + //perhaps make unique
                KEY_PASSWORD + " INTEGER NOT NULL, " +
                KEY_DESCRIPTION +" TEXT NOT NULL,"+
                "FOREIGN KEY("+ KEY_NAMECAT_BICYCLE +")"+" REFERENCES "+TABLE_CONTACTS+"("+KEY_NAME+")"+
                ")";

        String Laptop_Script = "CREATE TABLE If NOT EXISTS " + TABLE_LAPTOPPASS + "(" +
                KEY_LAPTOPOWNER_NAME  + " INTEGER NOT NULL, " +
                KEY_NAMECAT_LAPTOP+ " TEXT NOT NULL, " +
                KEY_LAPTOPMODEL+ " TEXT NOT NULL,"+ //perhaps make unique
                KEY_LAPTOPPASS + " TEXT NOT NULL, " +
                KEY_DESCRIPTION_LAPTOP +" TEXT NOT NULL,"+
                "FOREIGN KEY("+ KEY_NAMECAT_LAPTOP +")"+" REFERENCES "+TABLE_CONTACTS+"("+KEY_NAME+"),"+
                "CONSTRAINT ownerID PRIMARY KEY"+ "("+KEY_LAPTOPOWNER_NAME +", "+ KEY_LAPTOPMODEL+")"+
                ")";




        db.execSQL(Office_Script);
        db.execSQL(Bicycle_Script);
        db.execSQL(Laptop_Script);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);


        db.execSQL("drop table if exists " + TABLE_OFFICEPASS);

    }
    @Override
    public void onConfigure(SQLiteDatabase db){ db.setForeignKeyConstraintsEnabled(true); }
}

