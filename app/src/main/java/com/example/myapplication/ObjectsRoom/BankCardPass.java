package com.example.myapplication.ObjectsRoom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.myapplication.InformationSave.DateConverter;

import java.util.Date;


@Entity(tableName = "bankPaSswords",foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "catName", childColumns = "cardCat"))
public class BankCardPass {

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @NonNull
    @ColumnInfo(name ="cardCat")
    public String cardCat;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="cardNum")
    public int cardNum;

    @NonNull
    @ColumnInfo(name ="cardBank")
    public String cardBank;

    @NonNull
    @ColumnInfo(name ="cardPass")
    public int cardPass;

    @NonNull
    @ColumnInfo(name = "untilDate")
    @TypeConverters(DateConverter.class)
    public Date untilDate;

    @NonNull
    @ColumnInfo(name ="CVV")
    public int CVV;


    public BankCardPass(@NonNull String description, @NonNull String cardCat, int cardNum, @NonNull String cardBank, int cardPass, @NonNull Date untilDate, int CVV) {
        this.description = description;
        this.cardCat = cardCat;
        this.cardNum = cardNum;
        this.cardBank = cardBank;
        this.cardPass = cardPass;
        this.untilDate = untilDate;
        this.CVV = CVV;
    }

    public BankCardPass() {

    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getCardCat() {
        return cardCat;
    }

    public void setCardCat(@NonNull String cardCat) {
        this.cardCat = cardCat;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    @NonNull
    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(@NonNull String cardBank) {
        this.cardBank = cardBank;
    }

    public int getCardPass() {
        return cardPass;
    }

    public void setCardPass(int cardPass) {
        this.cardPass = cardPass;
    }

    @NonNull
    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(@NonNull Date untilDate) {
        this.untilDate = untilDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
}
