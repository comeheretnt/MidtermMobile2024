package com.midterm.phamnguyenhuyminh;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE id IN (:questionIds)")
    List<Question> loadAllByIds(int[] questionIds);

    @Insert
    void insertAll(Question... questions);


    @Delete
    void delete(Question question);

    @Delete
    void deleteAll(List<Question> questions);
}