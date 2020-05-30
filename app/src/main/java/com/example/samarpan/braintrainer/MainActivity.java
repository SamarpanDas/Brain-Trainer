package com.example.samarpan.braintrainer;

import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button goButton;
    TextView sumTextView;
    TextView resultTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectNumber;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timertextView;
    Button playAgain;
    ConstraintLayout gameLayout;
    GridLayout buttonGridLayout;


    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;
        timertextView.setText("30s");

        // Activating the buttons when play again is clicked
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);


        playAgain.setVisibility(View.INVISIBLE);


        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        resultTextView.setText(" ");


        newQuestions();
        // recreating the timer

        new CountDownTimer(30100,1000)
        {
            public void onTick(long l)
            {
                timertextView.setText(String.valueOf(l/1000) + "s");

            }

            public void onFinish()
            {
                resultTextView.setText("Done !");
                playAgain.setVisibility(View.VISIBLE);

                //Deactivating the buttons once the time is over
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);





            }

        }.start();








    }

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);

        playAgain(timertextView);  // the pasing variable is crap. Just pass in anything u wana.

        gameLayout.setVisibility(View.VISIBLE);

    }



    public void chooseAnswer(View view)
    {
        //view.getTag();

        //Log.i("Tag; ", view.getTag().toString());

        if(Integer.toString(locationOfCorrectNumber).equals(view.getTag().toString()))
        {
            Log.i("Correct ans", "OK");

            resultTextView.setText("Correct !");
            resultTextView.setVisibility(View.VISIBLE);
            score++;

        }
        else
        {
            Log.i("wrong ans", "OK");
            resultTextView.setText("Wrong !");
            resultTextView.setVisibility(View.VISIBLE);

        }

        numberOfQuestions++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestions();



    }





    public void newQuestions()
    {

        Random rand = new Random();

        int a = rand.nextInt(21);  //0 - 21
        int b = rand.nextInt(21);  // 0 - 21

        sumTextView.setText(Integer.toString(b) + " + " + Integer.toString(a));

        locationOfCorrectNumber = rand.nextInt(4);
        answers.clear();

        for(int i = 0;i<4;i++)
        {
            if(i == locationOfCorrectNumber)
                answers.add(a+b);

            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b)
                {
                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }










    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timertextView = findViewById(R.id.timerTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgain = findViewById(R.id.playAgainButton);
        buttonGridLayout = findViewById(R.id.buttonGridLayout);

        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


        //newQuestions();

        //playAgain(timertextView);  // the pasing variable is crap. Just pass in anything u wana.




    }
}
