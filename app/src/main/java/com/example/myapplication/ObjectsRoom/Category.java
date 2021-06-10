package com.example.myapplication.ObjectsRoom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
    public class Category {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "catName")
        public String catName;

        @ColumnInfo(name = "descriptions")
        public String description;

    public Category(String catName, String description) {
        this.catName = catName;
        this.description = description;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
