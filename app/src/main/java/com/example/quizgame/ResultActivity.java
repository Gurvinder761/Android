 package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

 public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView totalScoreLabel = (TextView)findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);
        totalScore += score;

        resultLabel.setText(score+ "/ 5");
        totalScoreLabel.setText("Total Score : " + totalScore);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore",totalScore);
        editor.commit();

        resultLabel.setText(score + "/ 5");
        totalScoreLabel.setText(" Score : " + score);

        if (score == 5) {
            resultLabel.setText("You are a Genius");
        } else if (score == 4) {
            resultLabel.setText("Excellent Work");
        } else if (score == 3) {
            resultLabel.setText("Good Job"); }
        else
            {
            resultLabel.setText("Try again");
        }

    }
}