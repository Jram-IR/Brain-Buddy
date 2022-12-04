package com.jayaram.cardmatcher;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface gameDAO {

    @Insert
    void insert(gameData gameData);

    @Update
    void update(gameData gameData);

    @Delete
    void delete(gameData gameData);

    @Query("DELETE FROM gameData_table")
    void  deleteAllData();


    @Query("SELECT * FROM gameData_table ORDER BY id DESC")
    LiveData<List<gameData>> getAllData();
}
