package com.example.abhishektask.database;

import android.content.Context;
import android.os.AsyncTask;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.abhishektask.DataConverter;


@Database(entities = Bio.class, version = 1,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class BioDatabase extends RoomDatabase {

    private static BioDatabase instance;

    public abstract BioDao bioDao();

    public static synchronized BioDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BioDatabase.class, "bio_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
