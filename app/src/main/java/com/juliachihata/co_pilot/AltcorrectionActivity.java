package com.juliachihata.co_pilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AltcorrectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altcorrection);
    }
}




    //t is temperature at airport that is rounded at 10th in case it isnt
    //h is height after calculation
    //A is the airport elevation that needs to be rounded to the 100th
    //check the video that is on ready, this is basically the steps , i still do not know how they went from having 2 heights values into 1.



//rounding airport elevation to the 100's
    public double roundAirportElevation(double A)
    {
    double A;

    A=Math.round(A*100)/100;

    return A;
    }
    public double Height(double h,double uncorrectedAltitude,double A){

    h=uncorrectedAltitude-A;

    return h;

    }

 //rounding t to the 10th
    public double roundTempValue(double t)
    {
        double t;

        t = Math.round(t *10) / 10;

        return t;
    }
    void coldCal(double t , double h, double newHeight1, double newHeight2){

// For temperature =0

        if (t==0 && h>=200 && h<300){
            newHeight1=20;
            newHeight2=20;
        }
        else if(t==0 && h>=300 && h<400){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=400 && h<500){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=500 && h<600){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=600 && h<700){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=700 && h<800){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=800 && h<900){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=900 && h<1000){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=1000 && h<1500){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=1500 && h<2000){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=2000 && h<3000){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=3000 && h<4000){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==0 && h>=4000 && h<=5000){
            newHeight1=20;
            newHeight2=30;
        }


//for tem= -10

        if (t==-10 && h>=200 && h<300){
            newHeight1=20;
            newHeight2=30;
        }
        else if(t==-10 && h>=300 && h<400){
            newHeight1=30;
            newHeight2=40;
        }
        else if(t==-10 && h>=400 && h<500){
            newHeight1=40;
            newHeight2=50;
        }
        else if(t==-10 && h>=500 && h<600){
            newHeight1=50;
            newHeight2=60;
        }
        else if(t==-10 && h>=600 && h<700){
            newHeight1=60;
            newHeight2=70;
        }
        else if(t==-10 && h>=700 && h<800){
            newHeight1=70;
            newHeight2=80;
        }
        else if(t==-10 && h>=800 && h<900){
            newHeight1=80;
            newHeight2=90;
        }
        else if(t==-10 && h>=900 && h<1000){
            newHeight1=90;
            newHeight2=100;
        }
        else if(t==-10 && h>=1000 && h<1500){
            newHeight1=100;
            newHeight2=150;
        }
        else if(t==-10 && h>=1500 && h<2000){
            newHeight1=150;
            newHeight2=200;
        }
        else if(t==-10 && h>=2000 && h<3000){
            newHeight1=200;
            newHeight2=290;
        }
        else if(t==-10 && h>=3000 && h<4000){
            newHeight1=290;
            newHeight2=390;
        }
        else if(t==-10 && h>=4000 && h<=5000){
            newHeight1=390;
            newHeight2=490;
        }


//For temp -20

        if (t==-20 && h>=200 && h<300){
            newHeight1=30;
            newHeight2=50;
        }
        else if(t==-20 && h>=300 && h<400){
            newHeight1=50;
            newHeight2=60;
        }
        else if(t==-20 && h>=400 && h<500){
            newHeight1=60;
            newHeight2=70;
        }
        else if(t==-20 && h>=500 && h<600){
            newHeight1=70;
            newHeight2=90;
        }
        else if(t==-20 && h>=600 && h<700){
            newHeight1=90;
            newHeight2=100;
        }
        else if(t==-20 && h>=700 && h<800){
            newHeight1=100;
            newHeight2=120;
        }
        else if(t==-20 && h>=800 && h<900){
            newHeight1=120;
            newHeight2=130;
        }
        else if(t==-20 && h>=900 && h<1000){
            newHeight1=130;
            newHeight2=140;
        }
        else if(t==-20 && h>=1000 && h<1500){
            newHeight1=140;
            newHeight2=210;
        }
        else if(t==-20 && h>=1500 && h<2000){
            newHeight1=210;
            newHeight2=280;
        }
        else if(t==-20 && h>=2000 && h<3000){
            newHeight1=280;
            newHeight2=420;
        }
        else if(t==-20 && h>=3000 && h<4000){
            newHeight1=420;
            newHeight2=570;
        }
        else if(t==-20 && h>=4000 && h<=5000){
            newHeight1=570;
            newHeight2=710;
        }

        //for temp -30

        if (t==-30 && h>=200 && h<300){
            newHeight1=40;
            newHeight2=60;
        }
        else if(t==-30 && h>=300 && h<400){
            newHeight1=60;
            newHeight2=80;
        }
        else if(t==-30 && h>=400 && h<500){
            newHeight1=80;
            newHeight2=100;
        }
        else if(t==-30 && h>=500 && h<600){
            newHeight1=100;
            newHeight2=120;
        }
        else if(t==-30 && h>=600 && h<700){
            newHeight1=120;
            newHeight2=140;
        }
        else if(t==-30 && h>=700 && h<800){
            newHeight1=140;
            newHeight2=150;
        }
        else if(t==-30 && h>=800 && h<900){
            newHeight1=150;
            newHeight2=170;
        }
        else if(t==-30 && h>=900 && h<1000){
            newHeight1=170;
            newHeight2=190;
        }
        else if(t==-30 && h>=1000 && h<1500){
            newHeight1=190;
            newHeight2=280;
        }
        else if(t==-30 && h>=1500 && h<2000){
            newHeight1=280;
            newHeight2=380;
        }
        else if(t==-30 && h>=2000 && h<3000){
            newHeight1=380;
            newHeight2=570;
        }
        else if(t==-30 && h>=3000 && h<4000){
            newHeight1=570;
            newHeight2=760;
        }
        else if(t==-30 && h>=4000 && h<=5000){
            newHeight1=760;
            newHeight2=950;
        }

        //for temp -40

        if (t==-40 && h>=200 && h<300){
            newHeight1=50;
            newHeight2=80;
        }
        else if(t==-40 && h>=300 && h<400){
            newHeight1=80;
            newHeight2=100;
        }
        else if(t==-40 && h>=400 && h<500){
            newHeight1=100;
            newHeight2=120;
        }
        else if(t==-40 && h>=500 && h<600){
            newHeight1=120;
            newHeight2=150;
        }
        else if(t==-40 && h>=600 && h<700){
            newHeight1=150;
            newHeight2=170;
        }
        else if(t==-40 && h>=700 && h<800){
            newHeight1=170;
            newHeight2=190;
        }
        else if(t==-40 && h>=800 && h<900){
            newHeight1=190;
            newHeight2=220;
        }
        else if(t==-40 && h>=900 && h<1000){
            newHeight1=220;
            newHeight2=240;
        }
        else if(t==-40 && h>=1000 && h<1500){
            newHeight1=240;
            newHeight2=360;
        }
        else if(t==-40 && h>=1500 && h<2000){
            newHeight1=360;
            newHeight2=480;
        }
        else if(t==-40 && h>=2000 && h<3000){
            newHeight1=480;
            newHeight2=720;
        }
        else if(t==-40 && h>=3000 && h<4000){
            newHeight1=720;
            newHeight2=970;
        }
        else if(t==-40 && h>=4000 && h<=5000){
            newHeight1=970;
            newHeight2=1210;
        }

        //For temp -50


        if (t==-50 && h>=200 && h<300){
            newHeight1=60;
            newHeight2=90;
        }
        else if(t==-50 && h>=300 && h<400){
            newHeight1=90;
            newHeight2=120;
        }
        else if(t==-50 && h>=400 && h<500){
            newHeight1=120;
            newHeight2=150;
        }
        else if(t==-50 && h>=500 && h<600){
            newHeight1=150;
            newHeight2=180;
        }
        else if(t==-50 && h>=600 && h<700){
            newHeight1=180;
            newHeight2=210;
        }
        else if(t==-50 && h>=700 && h<800){
            newHeight1=210;
            newHeight2=240;
        }
        else if(t==-50 && h>=800 && h<900){
            newHeight1=240;
            newHeight2=270;

    else if(t==-50 && h>=900 && h<1000){
                newHeight1=270;
                newHeight2=300;
            }
            else if(t==-50 && h>=1000 && h<1500){
                newHeight1=300;
                newHeight2=450;
            }
            else if(t==-50 && h>=1500 && h<2000){
                newHeight1=450;
                newHeight2=590;
            }
            else if(t==-50 && h>=2000 && h<3000){
                newHeight1=590;
                newHeight2=890;
            }
            else if(t==-50 && h>=3000 && h<4000){
                newHeight1=890;
                newHeight2=1190;
            }
            else if(t==-50 && h>=4000 && h<=5000){
                newHeight1=1190;
                newHeight2=1500;
            }
        }