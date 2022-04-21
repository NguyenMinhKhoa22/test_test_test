package com.example.room_database12;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface MainDao {

    @Insert(onConflict = REPLACE)
    void insertMainEntity(MainEntity mainEntity);

    @Query("SELECT * FROM note")
    LiveData<List<MainEntity>> getAllMainEntity();



    @Update
    void updateMainEntity(MainEntity mainEntity);

    @Delete
    void deleteMainEntity(MainEntity mainEntity);



}
