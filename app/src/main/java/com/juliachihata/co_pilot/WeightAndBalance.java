package com.juliachihata.co_pilot;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;

public class WeightAndBalance extends AppCompatActivity {

    protected EditText PlaneEditText;
    protected EditText FuelEditText;
    protected EditText PilotPassEditText;
    protected EditText BaggageAreaEditText;
    protected TextView SumMomentTextView;
    protected TextView SumWeightTextView;
    protected Button calculationButton;

    //initialize the variables that will hold the weight that need to be prompted by the user
    private double planeWeight;
    private double fuelWeight;
    private double pilotPassWeight;
    private double bagageAreaWeight;

    //set the values that correspond to the arm of the plane, these values are specific for Cessna172N
    protected static double armPlane=25.43056;
    protected static double armFuel=20.869;
    protected static double armPilotPass=39.5459;
    protected static double armBagageArea=10.54945;

    //initlialize the momentums that will be used in the calculation
    protected double momentumPlane;
    protected double momentumFuel;
    protected double momentumBagageArea;
    protected double momentumPilotPass;
    protected double totalMomentum;
    protected double totalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_and_balance);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
            //link the elements from the layout to the java activity
            PlaneEditText = (EditText) findViewById(R.id.PlaneEditText);
            FuelEditText = (EditText) findViewById(R.id.FuelEditText);
            PilotPassEditText = (EditText) findViewById(R.id.PilotPassEditText);
            BaggageAreaEditText = (EditText) findViewById(R.id.BaggageAreaEditText);
            SumMomentTextView = (TextView) findViewById(R.id.SumMomentTextView);
            SumWeightTextView = (TextView) findViewById(R.id.SumWeightTextView);
            calculationButton = findViewById(R.id.calculationButton);
    }


    public double sumMomentum() {
        momentumPlane = planeWeight * armPlane;
        momentumFuel = fuelWeight * armFuel;
        momentumBagageArea = bagageAreaWeight * armBagageArea;
        momentumPilotPass = pilotPassWeight * armPilotPass;
        totalMomentum = momentumPlane + momentumFuel + momentumBagageArea + momentumPilotPass;

        return totalMomentum;

    }
    public double sumWeights(){

        return totalWeight = planeWeight + fuelWeight + pilotPassWeight + bagageAreaWeight;
    }
    @Override
    protected void onStart() {
        super.onStart();
        calculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PlaneEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Warning: Field is empty", Toast.LENGTH_SHORT).show();

                }
                else if (FuelEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Warning: Field is empty", Toast.LENGTH_SHORT).show();

                }
                else if (PilotPassEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Warning: Field is empty", Toast.LENGTH_SHORT).show();

                }
                else if (BaggageAreaEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Warning: Field is empty", Toast.LENGTH_SHORT).show();

                }



                else if ((Double.parseDouble(FuelEditText.getText().toString())) > 100000 || (Double.parseDouble(PlaneEditText.getText().toString())) < 1000)
                    Toast.makeText(getApplicationContext(), "Warning: Fuel Weight must be between 1000 and 100,000", Toast.LENGTH_SHORT).show();

                else if ((Double.parseDouble(BaggageAreaEditText.getText().toString())) > 1000 || (Double.parseDouble(PlaneEditText.getText().toString())) < 0)
                    Toast.makeText(getApplicationContext(), "Warning: Baggage Weight must be between 0 and 1000", Toast.LENGTH_SHORT).show();

                else if ((Double.parseDouble(PlaneEditText.getText().toString())) > 2500 || (Double.parseDouble(PlaneEditText.getText().toString())) < 1000)
                    Toast.makeText(getApplicationContext(), "Warning: Plane Weight must be between 1000 and 2500", Toast.LENGTH_SHORT).show();

                else if ((Double.parseDouble(PilotPassEditText.getText().toString())) > 2000 || (Double.parseDouble(PlaneEditText.getText().toString())) < 50)
                    Toast.makeText(getApplicationContext(), "Warning: Passenger Weight must be between 1000 and 2500", Toast.LENGTH_SHORT).show();


                else
                {
                //converts the string into an integer and assigns it to the variable
                planeWeight=Double.parseDouble(PlaneEditText.getText().toString());
                fuelWeight=Double.parseDouble(FuelEditText.getText().toString());
                pilotPassWeight=Double.parseDouble(PilotPassEditText.getText().toString());
                bagageAreaWeight=Double.parseDouble(BaggageAreaEditText.getText().toString());


                SumMomentTextView.setText("Sum of Weight is \n"+sumWeights());
                SumWeightTextView.setText("Sum of Moment is "+sumMomentum());
            }}
        });
    }
}
