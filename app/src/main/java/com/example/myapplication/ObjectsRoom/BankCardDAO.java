package com.example.myapplication.ObjectsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface BankCardDAO {


    @Query("SELECT * FROM bankPaSswords")
    List<BankCardPass> getAll();

    @Query("SELECT * FROM bankPaSswords WHERE cardNum =:num")
    BankCardPass getById(int num);


    @Query("SELECT * FROM bankPaSswords WHERE cardCat=:item")
    List<BankCardPass> getNameCat(String item);

    @Insert
    void insert(BankCardPass card);

    @Update
    void update(BankCardPass card);

    @Delete
    void delete(BankCardPass card);
}
