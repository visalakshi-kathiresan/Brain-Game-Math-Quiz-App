package com.example.braingame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton,button0,button1,button2,button3,playagainbutton;
    TextView  sumText,textCorrect,textScore,textTimer;

    int locationOfCorrectAnswer;

    ArrayList<Integer> answer = new ArrayList<>();

    int score = 0;
    int numberOfGamesPlayed = 0;

    public void chooseAnswer(View view) {

        if(String.valueOf(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            textCorrect.setText("Correct!!");
            score++;
        }else{
            textCorrect.setText("Wrong!!");
        }
        numberOfGamesPlayed++;

        textScore.setText(score + "/" + numberOfGamesPlayed);

        newQuestion();
        playagainbutton.setVisibility(View.INVISIBLE);

    }

    public void playAgain(View view){

        score = 0;
        numberOfGamesPlayed = 0;
        textTimer.setText("30s");
        textScore.setText(score + "/" + numberOfGamesPlayed);

        newQuestion();

        CountDownTimer countDownTimer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                textCorrect.setText("Done");
                playagainbutton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void newQuestion() {


        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumText.setText(a + "+" + b);

        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        for (int i=0;i<4;i++){

            if(i == locationOfCorrectAnswer){
                answer.add(a+b);
            }else{

                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }

        button0.setText(String.valueOf(answer.get(0)));
        button1.setText(String.valueOf(answer.get(1)));
        button2.setText(String.valueOf(answer.get(2)));
        button3.setText(String.valueOf(answer.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.go);
        sumText = findViewById(R.id.sumText);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playagainbutton = findViewById(R.id.playAgainButton);

        textCorrect = findViewById(R.id.textcorrect);
        textScore  = findViewById(R.id.textScore);
        textTimer = findViewById(R.id.textTime);

        playAgain(findViewById(R.id.textcorrect));


    }
}

