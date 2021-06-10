package com.example.myapplication.ObjectsRoom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarmPasswords",foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "catName", childColumns = "nameCat"))
public class AlarmPasswords {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "OfficeID")
    public String OfficeID;
    @NonNull
    @ColumnInfo(name = "nameCat")
    public String nameCat;

    @NonNull
    @ColumnInfo(name = "password")
    public int password;

    @Nullable
    @ColumnInfo(name = "descriptionOffice")
    public String descriptionOffice;


    public AlarmPasswords() {
    }

    public AlarmPasswords(String officeID, String nameCat, int password, @Nullable String descriptionOffice) {
        OfficeID = officeID;
        this.nameCat = nameCat;
        this.password = password;
        this.descriptionOffice = descriptionOffice;
    }

    public String getOfficeID() {
        return OfficeID;
    }

    public void setOfficeID(String officeID) {
        OfficeID = officeID;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public  String getDescriptionOffice() {
        return descriptionOffice;
    }

    public void setDescriptionOffice(String descriptionOffice) {
        this.descriptionOffice = descriptionOffice;
    }

}
