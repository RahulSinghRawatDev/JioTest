package com.example.jiotest.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.jiotest.dao.TaskDao;
import com.example.jiotest.model.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao getTaskDao();

}
