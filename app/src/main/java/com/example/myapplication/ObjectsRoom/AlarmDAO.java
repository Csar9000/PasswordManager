package com.example.myapplication.ObjectsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlarmDAO {

    @Query("SELECT * FROM alarmPasswords")
    List<AlarmPasswords> getAll();

    @Query("SELECT * FROM alarmPasswords WHERE OfficeID = :catName")
    AlarmPasswords getByName(String catName);

    @Query("SELECT * FROM alarmPasswords WHERE nameCat=:item")
    List<AlarmPasswords> getNameCat(String item);

    @Insert
    void insert(AlarmPasswords alarm);

    @Update
    void update(AlarmPasswords alarm);

    @Delete
    void delete(AlarmPasswords alarm);

}
