package com.example.myapplication.ObjectsRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT catName FROM category")
    List<String> getAllCategories();

    @Query("SELECT DISTINCT catName FROM category WHERE catName=:item")
    String getNameCat(String item);

    @Query("SELECT * FROM category WHERE catName = :catName")
    Category getByName(String catName);

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

}
