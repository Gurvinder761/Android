package com.example.quizgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;


    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {

            {"What does HTML stands for?", "Hypertext Markup Language.","Hypertext Machine language.","Hypertext and links markup language.","Hightext machine language."},
            {"How is document type initialized in HTML5.?", "<!DOCTYPE HTML>","</DOCTYPE HTML>", "</DOCTYPE>","</DOCTYPE html>"},
            {"Which of the following HTML Elements is used for making any text bold ?","<b>","<p>","<i>","<li>" },
            {"Which of the following HTML element is used for creating an unordered list?","<ul>", "<ui>","<i>","<em>"},
            {"Which of the following characters indicate closing of a tag?","/", ".","\\","!"},
            {"What is the font-size of the h1 heading tag?","2 em","3.5 em","2.17 em","1.5 em"},
            {"How many heading tags are there in HTML5?","6","2","3","5"},
            {"Which of the following is the correct way of creating an hyperlink in HTML?", "<a href= “www.geeksforgeeks.org”>Geeksforgeeks</a>","<a>www.geeksforgeeks.org <Geeksforgeeks /a>","<a href=“www.geeksforgeeks.org” Geeksforgeeks /a>","<a link=“www.geeksforgeeks.org” Geeksforgeeks> </a>"},
            {"Which of the following attributes is used to add link to any element?", "href","link","ref","newref"}

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView) findViewById(R.id.countLabel);
        questionLabel = (TextView) findViewById(R.id.questionLabel);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);

        for (int i = 0; i < quizData.length; i++) {

            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }

    public void showNextQuiz() {
        countLabel.setText("Q" + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view) {


        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)) {
            alertTitle = "Correct";
            rightAnswerCount++;
        } else  {
            alertTitle = "Wrong";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
}