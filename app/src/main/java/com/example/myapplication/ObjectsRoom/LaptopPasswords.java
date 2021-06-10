package com.example.myapplication.ObjectsRoom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "laptopPasswords",foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "catName", childColumns = "nameCat"))
public class LaptopPasswords {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "serialNum")
    public String serialNumber;

    @NonNull
    @ColumnInfo(name = "laptopOwnerName")
    public String laptopOwnerName;
    @NonNull
    @ColumnInfo(name = "laptopModel")
    public String laptopModel;

    @NonNull
    @ColumnInfo(name = "nameCat")
    public String nameCat;

    @NonNull
    @ColumnInfo(name ="laptopPass")
    public  String laptopPass;

    @Nullable
    @ColumnInfo(name ="laptopDescription")
    public  String laptopDescription;


    public LaptopPasswords(@NonNull String serialNumber, @NonNull String laptopOwnerName, @NonNull String laptopModel, @NonNull String nameCat,@NonNull String laptopPass, @Nullable String laptopDescription) {
        this.serialNumber = serialNumber;
        this.laptopOwnerName = laptopOwnerName;
        this.laptopModel = laptopModel;
        this.nameCat = nameCat;
        this.laptopPass = laptopPass;
        this.laptopDescription = laptopDescription;
    }

    public LaptopPasswords() {

    }

    public String getLaptopOwnerName() {
        return laptopOwnerName;
    }

    public void setLaptopOwnerName(String laptopOwnerName) {
        this.laptopOwnerName = laptopOwnerName;
    }

    public String getLaptopModel() {
        return laptopModel;
    }

    public void setLaptopModel(String laptopModel) {
        this.laptopModel = laptopModel;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public  String getLaptopPass() {
        return laptopPass;
    }



    @NonNull
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(@NonNull String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setLaptopPass(@NonNull String laptopPass) {
        this.laptopPass = laptopPass;
    }

    public String getLaptopDescription() {
        return laptopDescription;
    }

    public void setLaptopDescription(String laptopDescription) {
        this.laptopDescription = laptopDescription;
    }
}
