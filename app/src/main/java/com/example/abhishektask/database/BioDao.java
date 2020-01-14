package com.example.abhishektask.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BioDao {
    @Insert
    void insert(Bio bio);

    @Update
    void update(Bio bio);

    @Delete
    void delete(Bio bio);

    @Query("DELETE FROM bio_table")
    void deleteAllBio();

    @Query("SELECT * FROM bio_table")
    LiveData<List<Bio>> getAllBio();
}
