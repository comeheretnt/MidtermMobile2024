package com.midterm.phamnguyenhuyminh;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;



import com.midterm.phamnguyenhuyminh.databinding.ActivityMainBinding;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    static MainActivity instance;
    ArrayList<Question> questionList;
    ArrayList<Question> userAnswerList;
    AppDatabase appDatabase;
    QuestionDAO questionDAO;
    int indexQuestion;

    public MainActivity() {
        instance = this;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appDatabase = AppDatabase.getInstance(getApplicationContext());
        questionDAO = appDatabase.questionDAO();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
////                questionDAO.insertAll(new Question("Washington is the capital of USA?", "True"));
////                questionDAO.insertAll(new Question("USa is the capital of Thailand?", "False"));
////                questionDAO.insertAll(new Question("HCM is the capital of Cambodia?", "False"));
//            }
//        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                questionList = (ArrayList<Question>) questionDAO.getAll();

                userAnswerList = new ArrayList<>(questionList);
                for (Question question : userAnswerList) {
                    question.setAnswer(null);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        indexQuestion = 0;
                        binding.textView.setText(userAnswerList.get(indexQuestion).getQuestion());
                    }
                });
            }
        });

        binding.btnTrue.setBackgroundColor(getResources().getColor(R.color.yellow));
        binding.btnFalse.setBackgroundColor(getResources().getColor(R.color.yellow));

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexQuestion > 0) {
                    indexQuestion--;
                    binding.textView.setText(userAnswerList.get(indexQuestion).getQuestion());
                    checkButton();
                }
            }
        });
        binding.btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexQuestion < userAnswerList.size() - 1) {
                    indexQuestion++;
                    binding.textView.setText(userAnswerList.get(indexQuestion).getQuestion());
                    checkButton();
                }
            }
        });

        binding.btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswerList.get(indexQuestion).setAnswer("True");
                checkButton();
            }
        });
        binding.btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswerList.get(indexQuestion).setAnswer("False");
                checkButton();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });


    }

    void checkButton() {
        String userAnswer = userAnswerList.get(indexQuestion).getAnswer();
        if (userAnswer != null) {
            if (userAnswer.equals("True")) {
                binding.btnTrue.setBackgroundColor(getResources().getColor(R.color.pink));
                binding.btnFalse.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                binding.btnTrue.setBackgroundColor(getResources().getColor(R.color.pink));
                binding.btnFalse.setBackgroundColor(getResources().getColor(R.color.green));
            }
        } else {
            binding.btnTrue.setBackgroundColor(getResources().getColor(R.color.yellow));
            binding.btnFalse.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }
}
