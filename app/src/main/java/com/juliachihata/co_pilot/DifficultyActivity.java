package com.juliachihata.co_pilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class DifficultyActivity extends AppCompatActivity {

    TextView efttext;
    SeekBar eftbar;
    RadioGroup missiongroup;
    RadioButton gbutton;
    RadioButton abutton;
    TextView diftext;
    SeekBar difbar;
    Button savebutton;


    public void updateeft(int eft){
        int hours = eft/4;
        int minutes = (eft*15) - (hours*60);
        String minString = Integer.toString(minutes);
        String hourString = Integer.toString(hours);
        if(minutes <= 9){
            minString = "0" + minString;
        }
        if(hours <= 9){
            hourString = "0" + hourString;
        }
        efttext.setText(hourString + ":" + minString);
    }


    public void updatedif(int dif){
        String difficulty = "MEDIUM";

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //back button
        getSupportActionBar().setTitle("Awareness");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_difficulty);

        efttext = findViewById(R.id.eft_text);
        eftbar = findViewById(R.id.eft_bar);
        diftext = findViewById(R.id.difficulty_text);
        difbar = findViewById(R.id.difficulty_bar);

        eftbar.setMax(20);
        eftbar.setMin(1);
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

    }
}
