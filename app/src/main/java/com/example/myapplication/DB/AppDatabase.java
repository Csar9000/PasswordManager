package com.example.myapplication.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.ObjectsRoom.AlarmDAO;
import com.example.myapplication.ObjectsRoom.AlarmPasswords;
//import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.ObjectsRoom.BankCardDAO;
import com.example.myapplication.ObjectsRoom.BankCardPass;
import com.example.myapplication.ObjectsRoom.BicycleDAO;
import com.example.myapplication.ObjectsRoom.BicyclePasswords;
import com.example.myapplication.ObjectsRoom.Category;
import com.example.myapplication.ObjectsRoom.CategoryDAO;
import com.example.myapplication.ObjectsRoom.LaptopDAO;
import com.example.myapplication.ObjectsRoom.LaptopPasswords;


@Database(entities = {Category.class,LaptopPasswords.class,AlarmPasswords.class,BicyclePasswords.class, BankCardPass.class}, version = 1, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract CategoryDAO CatDao();
        public abstract AlarmDAO AlarmDAO();
        public abstract BicycleDAO BicycleDAO();
        public abstract LaptopDAO LaptopDAO();
        public abstract BankCardDAO BankCardDAO();
}
