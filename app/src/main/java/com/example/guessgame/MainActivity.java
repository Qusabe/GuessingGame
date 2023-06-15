package com.example.guessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    private Toolbar mToolbar;
    private TextView result;
    private Button btnGuess;
    private int theNumber;
    private EditText enterNum;

    public void newGame() { theNumber = (int)(Math.random() * 100 + 1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitle("Nikita Tabakar Ho-Fi Guessing Game");

        result = findViewById(R.id.result);
        btnGuess = findViewById(R.id.btnGuess);
        enterNum = findViewById(R.id.enterNum);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkGuess();
            }
        });

        enterNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return false;
            }
        });
        newGame();

    }

    public void checkGuess() {
        String guessText = enterNum.getText().toString();

        String message = "";
        try {
            int guess = Integer.parseInt(guessText);
            if (guess < theNumber)
                message = guess + " is too low. Try again.";
            else if (guess > theNumber)
                message = guess + " is too high. Try again.";
            else {
                message = guess + " is correct. You win! Let's play again!"; newGame();
            }
        } catch (Exception e) {
            message = "Enter a whole number between 1 and 100.";
        } finally {
            result.setText(message);
            enterNum.requestFocus();
            enterNum.selectAll();
        }
    }
}
