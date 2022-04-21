package com.example.room_database12;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainEntity.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    // CREATE DAO
    public abstract MainDao mainDao();

    // CREATE DATABASE INSTANCE
    public static volatile MainDatabase INSTANCE;

    static MainDatabase getDatabase(Context context) {
        if (INSTANCE == null){
            synchronized (MainDatabase.class) {
                if (INSTANCE == null) {
                     INSTANCE = (MainDatabase) Room.databaseBuilder(context, MainDatabase.class, "note.db").build();
                }
            }
        }

        return INSTANCE;
    }

}
