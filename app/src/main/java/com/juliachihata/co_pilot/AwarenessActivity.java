package com.juliachihata.co_pilot;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AwarenessActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton;
    int red = android.R.color.holo_red_dark;
    int green = android.R.color.holo_green_dark;
    CountDownTimer countDownTimer;
    int check = 0;

    public void resetTimer() {
        timerTextView.setText("00:10:00");
        timerSeekBar.setProgress(600);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Start Flight");
        goButton.setBackgroundResource(green);
        counterIsActive = false;
    }


    public void buttonClicked(View view) {

        if (counterIsActive) {

            resetTimer();

        } else {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setBackgroundResource(red);
             goButton.setText("Stop Flight");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {
        int hours = secondsLeft / 3600;
        int minutes = (secondsLeft / 60) - (hours * 60);
        int seconds = secondsLeft - (minutes * 60) - (hours * 3600);


        String secondString = Integer.toString(seconds);
        String thirdString = Integer.toString(minutes);
        String fourthString = Integer.toString(hours);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        if (minutes <= 9) {
            thirdString = "0" + thirdString;
        }
        if (hours <= 9) {
            fourthString = "0" + fourthString;
        }



        timerTextView.setText(fourthString+ ":" + thirdString + ":" + secondString);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);

        if(check == 0){
            Intent intent= new Intent(AwarenessActivity.this, DifficultyActivity.class);
            startActivity(intent);
        }

        timerSeekBar = findViewById(R.id.tr_seekbar);
        timerTextView = findViewById(R.id.tr_edittext);
        goButton = findViewById(R.id.startflight_button);

        timerSeekBar.setMax(36000);
        timerSeekBar.setMin(600);
        timerSeekBar.setProgress(600);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
