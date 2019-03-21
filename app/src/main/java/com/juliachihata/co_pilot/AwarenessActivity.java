package com.juliachihata.co_pilot;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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
    Button goButton;
    ImageButton settingsButton;
    int red = android.R.color.holo_red_dark;
    int green = android.R.color.holo_green_dark;
    CountDownTimer countDownTimer;
    long endTime;
    long timeleftms;
    int eft, difficulty;
    AlarmManager alarmManager;


    public void resetTimer() {
        timerTextView.setText("00:10:00");
        timerSeekBar.setProgress(600);
        timerSeekBar.setEnabled(true);
        goButton.setText("Start Flight");
        goButton.setBackgroundResource(green);
        settingsButton.setClickable(true);
        countDownTimer.cancel();
        counterIsActive = false;
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("settingsaved",false);
    }


    private void startTimer() {
        endTime = System.currentTimeMillis() + timeleftms;
        Toast.makeText(getApplicationContext(),timeleftms+"",Toast.LENGTH_SHORT).show();
        if(alarmManager == null){
            startAlarm(timeleftms);
        }
        else{

        }

        countDownTimer = new CountDownTimer(timeleftms, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleftms = millisUntilFinished;
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                counterIsActive = false;
                resetTimer();
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
        timerTextView.setText(fourthString+ ":" + thirdString + ":" + secondString);

        if(secondsLeft <= 10 && secondsLeft > 0){
            timerTextView.setText(secondString);
        }
        else if(secondsLeft == 0){

        }

    }



    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
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
        boolean settings = preferences.getBoolean("settingsaved",false);
        timeleftms = preferences.getLong("timeleft", 600*1000);
        counterIsActive = preferences.getBoolean("Active",false);
        if( counterIsActive == false && settings == false){
            Intent intent= new Intent(AwarenessActivity.this, DifficultyActivity.class);
            startActivity(intent);
        }

        eft = preferences.getInt("eft",3600);
        difficulty = preferences.getInt("difficulty",1);
        timerSeekBar.setMax(eft/2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timerSeekBar.setMin(10);
        }
        timerSeekBar.setProgress(10);
        updateTimer(timerSeekBar.getProgress());

        if(counterIsActive){
            endTime = preferences.getLong("endtime", 0);
            timeleftms = endTime - System.currentTimeMillis();

            if(timeleftms < 0){
                timeleftms = 0;
                counterIsActive = false;
                resetTimer();
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


        //back button
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //font
        normal_font = (TextView)findViewById(R.id.tr_edittext);
        myfont2 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
        normal_font.setTypeface(myfont2);

        normal_font = (TextView)findViewById(R.id.tr_textview);
        myfont2 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
        normal_font.setTypeface(myfont2);

        normal_font = (TextView)findViewById(R.id.settings_textview);
        myfont2 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
        normal_font.setTypeface(myfont2);


        timerSeekBar = findViewById(R.id.tr_seekbar);
        timerTextView = findViewById(R.id.tr_edittext);
        goButton = findViewById(R.id.startflight_button);
        settingsButton = findViewById(R.id.settings_button);

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



    }


    private void startAlarm(long time) {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MILLISECOND, (int)time-1000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

}
