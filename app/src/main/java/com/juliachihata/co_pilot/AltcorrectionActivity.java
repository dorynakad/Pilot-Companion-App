package com.juliachihata.co_pilot;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class AltcorrectionActivity extends AppCompatActivity {
    EditText tempEditText, altEditText, uncoraltEditText;
    TextView adjaltTextView, correctedaltTextView;
    Button calculateButton;
    int temp, alt, uncoralt, adjalt, correctedalt, height;
    String tempS, altS, unCorAltS;


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

        int maxLength[]= new int[]{3,4,5};

        //to limit characters
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength[0]);
        tempEditText.setFilters(FilterArray);
        FilterArray[0] = new InputFilter.LengthFilter(maxLength[1]);
        altEditText.setFilters(FilterArray);
        FilterArray[0] = new InputFilter.LengthFilter(maxLength[2]);
        uncoraltEditText.setFilters(FilterArray);
    }

    @Override
    protected void onStart() {
        super.onStart();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempS = tempEditText.getText().toString();
                altS = altEditText.getText().toString();
                unCorAltS = uncoraltEditText.getText().toString();

                if (!tempS.isEmpty() && !altS.isEmpty() && !unCorAltS.isEmpty()) {
                    temp = Integer.parseInt(tempS);
                    alt = Integer.parseInt(altS);
                    uncoralt = Integer.parseInt(unCorAltS);

                    if (temp < -40 || temp > 10){
                        Toast.makeText(getApplicationContext(),"Error: -40 ≤ temperature ≤ 10",Toast.LENGTH_SHORT).show();
                    }
                    else if (alt < 200 || alt > 5000){
                        Toast.makeText(getApplicationContext(),"Error: 200 ≤ altitude ≤ 5000",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        alt = roundAirportElevation(alt);
                        temp = roundTempValue(temp);
                        height = height(uncoralt, alt);
                        adjalt = coldCal(temp, height);
                        correctedalt = uncoralt + adjalt;
                        adjaltTextView.setText(""+adjalt);
                        correctedaltTextView.setText(""+correctedalt);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Warning: Field is empty",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    //rounding airport elevation to the 100's
    public int roundAirportElevation(int a)
    {
        return Math.round(a * 100) / 100;
    }

    public int height(int uncorrectedAltitude, int a){
        return uncorrectedAltitude - a;
    }

    //rounding t to the 10th
    public int roundTempValue(int t)
    {
        return Math.round(t * 10) / 10;
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