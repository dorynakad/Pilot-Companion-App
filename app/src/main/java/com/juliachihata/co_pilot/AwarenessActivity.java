package com.juliachihata.co_pilot;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AwarenessActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button goButton,contButton;
    ImageButton settingsButton;
    int red = android.R.color.holo_red_dark;
    int green = android.R.color.holo_green_dark;
    int mission;
    int startmission;
    CountDownTimer countDownTimer;
    long endTime;
    long timeleftms,total;
    int eft, difficulty,progress;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;


    public void resetTimer() {
        progress = timerSeekBar.getProgress();
        updateTimer(progress);
        timerSeekBar.setProgress(progress);
        timerSeekBar.setEnabled(true);
        goButton.setText("Start Flight");
        goButton.setBackgroundResource(green);
        settingsButton.setClickable(true);
        contButton.setVisibility(View.INVISIBLE);

        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        counterIsActive = false;
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("settingsaved",false);
    }


    private void startTimer() {
        contButton.setVisibility(View.INVISIBLE);
        endTime = System.currentTimeMillis() + timeleftms;
        Toast.makeText(getApplicationContext(),timeleftms+"",Toast.LENGTH_SHORT).show();
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("startmission",0);
        editor.commit();

        if(alarmManager == null){
            startAlarm(timeleftms);
        }
        countDownTimer = new CountDownTimer(timeleftms, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftms = millisUntilFinished;
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                progress = timerSeekBar.getProgress();
                updateTimer(progress);
                timerSeekBar.setProgress(progress);
                contButton.setVisibility(View.VISIBLE);
                if(mission == 1){
                    Intent intent= new Intent(AwarenessActivity.this, GestureActivity.class);
                    startActivity(intent);
                }
                else if(mission == 2){
                    Intent intent= new Intent(AwarenessActivity.this, GestureActivity.class);
                    startActivity(intent);
                }
                SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("startmission",1);
                editor.commit();

            }
        }.start();

        counterIsActive = true;
        settingsButton.setClickable(false);
        timerSeekBar.setEnabled(false);
        goButton.setBackgroundResource(red);
        goButton.setText("Stop Flight");
    }

    public void updateTimer(int secondsLeft) {
        timeleftms = secondsLeft * 1000;
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
        if(seconds == 0 && minutes == 0 && hours == 0){
            secondString = "00";
            thirdString = "00";
            fourthString = "00";
        }
        timerTextView.setText(fourthString+ ":" + thirdString + ":" + secondString);

        if(secondsLeft <= 10 && secondsLeft > 0){
            timerTextView.setText(secondString);
        }
        else if(secondsLeft == 0){

            timerTextView.setText(fourthString + ":" + thirdString + ":" + secondString);


        if(secondsLeft <= 10 && secondsLeft > 0){
            timerTextView.setText(secondString);
        }
        else if(secondsLeft <= 0){
            timerTextView.setText("0");
        }

    }



    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("progress",progress);
        editor.putBoolean("Active",counterIsActive);
        editor.putLong("endtime", endTime);
        editor.putLong("timeleft", timeleftms);
        editor.commit();


        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        boolean settings = preferences.getBoolean("settingsaved",false);

        timeleftms = preferences.getLong("timeleft", 600*1000);
        counterIsActive = preferences.getBoolean("Active",false);
        progress = preferences.getInt("progress", 10);
        if( counterIsActive == false && settings == false){
            Intent intent= new Intent(AwarenessActivity.this, DifficultyActivity.class);
            startActivity(intent);
        }
        eft = preferences.getInt("eft",60000);
        total = System.currentTimeMillis() + eft;
        difficulty = preferences.getInt("difficulty",1);
        mission = preferences.getInt("mission", 1);
        timerSeekBar.setMax(eft/2);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timerSeekBar.setMin(10);
        }

        timerSeekBar.setProgress(progress);
        updateTimer(timerSeekBar.getProgress());

        if(counterIsActive){
            endTime = preferences.getLong("endtime", 0);
            timeleftms = endTime - System.currentTimeMillis();

            if(timeleftms  <= 0){
                startmission = preferences.getInt("startmission",0);
                System.out.println(startmission+"");
                if(startmission == 0) {
                    if (mission == 1) {
                        Intent intent = new Intent(AwarenessActivity.this, GestureActivity.class);
                        startActivity(intent);
                    } else if (mission == 2) {
                        Intent intent = new Intent(AwarenessActivity.this, GestureActivity.class);
                        startActivity(intent);
                    }
                    editor.putInt("startmission",1);
                    System.out.println(startmission+"");
                    editor.commit();
                }
                System.out.println(startmission+"");
                progress = timerSeekBar.getProgress();
                updateTimer(progress);
                timerSeekBar.setProgress(progress);
                counterIsActive = false;
                contButton.setVisibility(View.VISIBLE);
                counterIsActive = true;
                settingsButton.setClickable(false);
                timerSeekBar.setEnabled(false);
                goButton.setBackgroundResource(red);
                goButton.setText("Stop Flight");

            }
            else {
                startTimer();
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timerSeekBar = findViewById(R.id.tr_seekbar);
        timerTextView = findViewById(R.id.tr_edittext);
        goButton = findViewById(R.id.startflight_button);
        contButton = findViewById(R.id.continue_button);
        settingsButton = findViewById(R.id.settings_button);
        goButton.setVisibility(View.VISIBLE);
        contButton.setVisibility(View.INVISIBLE);

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

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AwarenessActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterIsActive == true) {
                    resetTimer();
                }

                else {
                    endTime = System.currentTimeMillis()/1000 + timerSeekBar.getProgress();
                    startTimer();
                }
            }
        });

        contButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    endTime = System.currentTimeMillis()/1000 + timerSeekBar.getProgress();
                    startTimer();
            }
        });

    }

    private void startAlarm(long time) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MILLISECOND, (int)time-1000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

}
