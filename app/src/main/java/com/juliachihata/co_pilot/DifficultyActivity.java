package com.juliachihata.co_pilot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.juliachihata.co_pilot.AwarenessActivity;

public class DifficultyActivity extends AppCompatActivity {

    //TextView normal_font;
    //Typeface myfont3;

    TextView efttext;
    SeekBar eftbar;
    RadioButton ari,ges;
    RadioButton missionbutton;
    TextView diftext;
    SeekBar difbar;
    Button savebutton;
    String checked;
    int mission;
    int hours;
    int minutes;
    int total;
    int difint;
    AwarenessActivity awarenessActivity = new AwarenessActivity();
    View view;



    public void updateeft(int eft){
        hours = eft/4;
        minutes = (eft*15) - (hours*60);
        String minString = Integer.toString(minutes);
        String hourString = Integer.toString(hours);
        total = hours*3600 + minutes*60;
        if(minutes <= 9){
            minString = "0" + minString;
        }
        if(hours <= 9){
            hourString = "0" + hourString;
        }
        efttext.setText(hourString + ":" + minString + ":00");

        saveinfo();
        savebutton.setClickable(true);
    }


    public void updatedif(int dif){
        String difficulty = "MEDIUM";
        difint = dif;
        saveinfo();
        savebutton.setClickable(true);
        if(dif == 0){
            difficulty = "EASY";
        }
        else if(dif == 1){
            difficulty = "MEDIUM";
        }
        else if(dif == 2){
            difficulty = "HARD";
        }
        diftext.setText(difficulty);
    }



    public void saveinfo(){
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("eft",total);
        editor.putInt("difficulty",difint);
        editor.putBoolean("settingsaved",true);
        savebutton.setClickable(false);
        editor.commit();
    }






    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        //font
       // normal_font = (TextView)findViewById(R.id.eft_textview);
//        myfont3 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
//        normal_font.setTypeface(myfont3);
//
//        normal_font = (TextView)findViewById(R.id.eft_text);
//        myfont3 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
//        normal_font.setTypeface(myfont3);
//
//        normal_font = (TextView)findViewById(R.id.md_textview);
//        myfont3 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
//        normal_font.setTypeface(myfont3);
//
//        normal_font = (TextView)findViewById(R.id.textView11);
//        myfont3 = Typeface.createFromAsset(this.getAssets(),"fonts/GeosansLight.ttf");
//        normal_font.setTypeface(myfont3);


        //back button
        getSupportActionBar().setTitle("Awareness");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        efttext = findViewById(R.id.eft_text);
        eftbar = findViewById(R.id.eft_bar);
        diftext = findViewById(R.id.difficulty_text);
        difbar = findViewById(R.id.difficulty_bar);
        savebutton = findViewById(R.id.savedifficulty_button);

        eftbar.setMax(20);
        eftbar.setMin(2);
        eftbar.setProgress(10);

        difbar.setMax(2);
        difbar.setProgress(1);

        eftbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateeft(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        difbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updatedif(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

}
