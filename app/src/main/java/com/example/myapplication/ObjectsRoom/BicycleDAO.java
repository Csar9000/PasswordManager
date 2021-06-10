package com.example.myapplication.ObjectsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BicycleDAO {

    @Query("SELECT * FROM bicyclePasswords")
    List<BicyclePasswords> getAll();

    @Query("SELECT * FROM bicyclePasswords WHERE ownerName = :ownerName")
    BicyclePasswords getByName(String ownerName);

    @Query("SELECT * FROM bicyclePasswords WHERE nameCat=:item")
    List<BicyclePasswords> getNameCat(String item);

    @Insert
    void insert(BicyclePasswords bicyclePasswords);

    @Update
    void update(BicyclePasswords bicyclePasswords);

    @Delete
    void delete(BicyclePasswords bicyclePasswords);

}
