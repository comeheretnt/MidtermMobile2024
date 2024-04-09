package com.midterm.phamnguyenhuyminh;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String question;
    @ColumnInfo
    private String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
