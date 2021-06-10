package com.example.myapplication.ObjectsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LaptopDAO {

    @Query("SELECT * FROM laptopPasswords")
    List<LaptopPasswords> getAll();

    @Query("SELECT * FROM laptopPasswords WHERE serialNum =:serial")
    LaptopPasswords getById(String serial);


    @Query("SELECT * FROM laptopPasswords WHERE nameCat=:item")
    List<LaptopPasswords> getNameCat(String item);

    @Insert
    void insert(LaptopPasswords laptopPasswords);

    @Update
    void update(LaptopPasswords laptopPasswords);

    @Delete
    void delete(LaptopPasswords laptopPasswords);

}

