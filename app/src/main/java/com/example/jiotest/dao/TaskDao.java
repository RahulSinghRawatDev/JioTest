package com.example.jiotest.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jiotest.model.Task;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    Maybe<List<Task>> getAllTask();

    @Insert
    long addTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

}
