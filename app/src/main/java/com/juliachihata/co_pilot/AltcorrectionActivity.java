package com.juliachihata.co_pilot;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class AltcorrectionActivity extends AppCompatActivity {
    EditText tempEditText, altEditText, uncoraltEditText;
    TextView adjaltTextView,correctedaltTextView;
    Button calculateButton;
    int temp, alt,uncoralt,adjalt,correctedalt, height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altcorrection);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }

        tempEditText = findViewById(R.id.temptext);
        altEditText = findViewById(R.id.alt_edittext);
        uncoraltEditText = findViewById(R.id.unalt_edittext);
        adjaltTextView = findViewById(R.id.adj_textview);
        correctedaltTextView = findViewById(R.id.correctedalt_textview);
        calculateButton = findViewById(R.id.calculate_button);
    }

    @Override
    protected void onStart() {
        super.onStart();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = Integer.parseInt(tempEditText.getText().toString());
                alt = Integer.parseInt(altEditText.getText().toString());
                uncoralt = Integer.parseInt(uncoraltEditText.getText().toString());

                alt = roundAirportElevation(alt);
                temp = roundTempValue(temp);
                height = height(uncoralt,alt);
                adjalt = coldCal(temp,height);
                correctedalt = uncoralt + adjalt;
                adjaltTextView.setText(""+adjalt);
                correctedaltTextView.setText(""+correctedalt);

            }
        });



    }

    //rounding airport elevation to the 100's
    public int roundAirportElevation(int a)
    {
        int A;
        A=Math.round(a*100)/100;

        return A;
    }

    public int height(int uncorrectedAltitude,int a){
        int h;
        h=uncorrectedAltitude-a;

        return h;
    }

    //rounding t to the 10th
    public int roundTempValue(int t)
    {
        int T;

        T = Math.round(t *10) / 10;

        return T;
    }

    int coldCal(int t , int h) {
        int chart;
        int newh = 0;


        if (t == 0) {

            if (h >= 200 && h < 250) {
                chart = 20;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 20;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 30;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 30;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 40;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 40;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 50;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 50;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 60;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 90;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 120;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 170;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 230;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 280;
                newh = (chart * h) / 5000;
            }

        }

//for tem= -10

        else if (t == -10) {

            if (h >= 200 && h < 250) {
                chart = 20;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 30;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 40;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 50;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 60;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 70;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 80;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 90;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 100;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 150;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 200;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 290;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 390;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 490;
                newh = (chart * h) / 5000;
            }

        }


//For temp -20

        else if (t == -20) {

            if (h >= 200 && h < 250) {
                chart = 30;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 50;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 60;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 70;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 90;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 100;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 120;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 130;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 140;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 210;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 280;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 420;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 570;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 710;
                newh = (chart * h) / 5000;
            }

        }


        //for temp -30

        else if (t == -30) {

            if (h >= 200 && h < 250) {
                chart = 40;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 60;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 80;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 100;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 120;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 140;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 150;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 170;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 190;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 280;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 380;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 570;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 760;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 950;
                newh = (chart * h) / 5000;
            }

        }


        //for temp -40

        else if (t == -40) {

            if (h >= 200 && h < 250) {
                chart = 50;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 80;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 100;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 120;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 150;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 170;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 190;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 220;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 240;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 360;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 480;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 720;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 970;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 1210;
                newh = (chart * h) / 5000;
            }

        }


        //For temp -50


        else if (t == -50) {

            if (h >= 200 && h < 250) {
                chart = 60;
                newh = (chart * h) / 200;
            } else if (h >= 250 && h < 350) {
                chart = 90;
                newh = (chart * h) / 300;
            } else if (h >= 350 && h < 450) {
                chart = 120;
                newh = (chart * h) / 400;
            } else if (h >= 450 && h < 550) {
                chart = 150;
                newh = (chart * h) / 500;
            } else if (h >= 550 && h < 650) {
                chart = 180;
                newh = (chart * h) / 600;
            } else if (h >= 650 && h < 750) {
                chart = 210;
                newh = (chart * h) / 700;
            } else if (h >= 750 && h < 850) {
                chart = 240;
                newh = (chart * h) / 800;
            } else if (h >= 850 && h < 950) {
                chart = 270;
                newh = (chart * h) / 900;
            } else if (h >= 950 && h < 1250) {
                chart = 300;
                newh = (chart * h) / 1000;
            } else if (h >= 1250 && h < 1750) {
                chart = 450;
                newh = (chart * h) / 1500;
            } else if (h >= 1750 && h < 2500) {
                chart = 590;
                newh = (chart * h) / 2000;
            } else if (h >= 2500 && h < 3500) {
                chart = 890;
                newh = (chart * h) / 3000;
            } else if (h >= 3500 && h <= 4500) {
                chart = 1190;
                newh = (chart * h) / 4000;
            } else if (h >= 4500 && h <= 5000) {
                chart = 1500;
                newh = (chart * h) / 5000;
            }

        }

        else {
            newh = 0;
        }

        return newh;
        }

    }