package com.example.myapplication.ObjectsRoom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "bicyclePasswords",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "catName", childColumns = "nameCat"))
public class BicyclePasswords {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ownerName")
    public String ownerName;

    @ColumnInfo(name = "nameCat")
    public String nameCat;

    @ColumnInfo(name = "password")
    public int password;

    @Nullable
    @ColumnInfo(name = "description")
    public String description;

    public BicyclePasswords(String ownerName, String nameCat, int password, @Nullable String description) {
        this.ownerName = ownerName;
        this.nameCat = nameCat;
        this.password = password;
        this.description = description;
    }

    public BicyclePasswords() {
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
