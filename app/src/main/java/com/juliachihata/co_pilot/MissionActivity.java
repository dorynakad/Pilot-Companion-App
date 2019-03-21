package com.juliachihata.co_pilot;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Random;

public class MissionActivity extends AppCompatActivity {

    TextView sequenceTextView;
    TextView infoTextView;
    TextView secondsTextView;
    TextView roundsTextView;
    int difficulty;
    long seconds;
    int repeat, i,random;
    CountDownTimer timer;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);


        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sequenceTextView = findViewById(R.id.sqnc_edittext);
        infoTextView = findViewById(R.id.info_edittext);
        secondsTextView = findViewById(R.id.seconds_edittext);
        roundsTextView = findViewById(R.id.rounds_edittext);

        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        difficulty = preferences.getInt("difficulty",1);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(difficulty == 0){
            seconds = 11000;
            repeat = 3;
        }

        else if(difficulty == 1){
            seconds = 7000;
            repeat = 6;
        }

        else if(difficulty == 2){
            seconds = 4000;
            repeat = 9;
        }

        arrowThread run1 = new arrowThread(repeat,seconds);
        timerThread run2 = new timerThread(repeat,seconds);
        Thread at = new Thread(run1);
        Thread tt = new Thread(run2);
        at.start();
        tt.start();



    }


    class timerThread implements Runnable{

        long seconds;
        int repeat;
        int time;
        int h,i;

        timerThread(int repeat,long seconds){
            this.repeat = repeat;
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for(h = 0; h < repeat; h++){
            for(i = 0; i < seconds/1000; i++) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time = (int) seconds / 1000 - i-1;
                        secondsTextView.setText(time + "");

                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            }

        }
    }


    class arrowThread implements Runnable {

        int repeat;
        long seconds;
        int i;
        arrowThread(int repeat,long seconds){
            this.repeat = repeat;
            this.seconds = seconds;
        }

        @Override
        public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roundsTextView.setText((i+1)+"/"+(repeat));
                        random = rand.nextInt(4)+1;
                        if(random == 1){
                            sequenceTextView.setText("←");
                        }
                        else if(random == 2){
                            sequenceTextView.setText("→");
                        }
                        else if(random == 3){
                            sequenceTextView.setText("↑");
                        }
                        else if(random == 4){
                            sequenceTextView.setText("↓");
                        }

                        try {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }

    }

}
