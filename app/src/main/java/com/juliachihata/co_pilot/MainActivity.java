package com.juliachihata.co_pilot;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //setting text font for textview in main activity xml
    TextView mytv;
    Typeface myfont;
    BluetoothAdapter myBluetooth = null;
    Button but1, but1_1, but1_2, but2, but3, but4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("stop",0);
        editor.commit();

        //Get the ref of textview in the code
        mytv = (TextView)findViewById(R.id.copilot_title);
        myfont = Typeface.createFromAsset(this.getAssets(),"fonts/forced_square.ttf");
        mytv.setTypeface(myfont);

        //setting buttons for main activity
        but1=(Button) findViewById(R.id.calculations_button);
        but1_1=(Button) findViewById(R.id.altcorrection_button);
        but1_2=(Button) findViewById(R.id.weightbalancebutton);
        but2=(Button) findViewById(R.id.awareness_button);
        but3=(Button) findViewById(R.id.dictionary_button);
        but4=(Button) findViewById(R.id.logbook_button);

        if(!myBluetooth.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
        
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                but1.setVisibility(View.INVISIBLE);
                but1_1.animate().alpha(1.0f).setDuration(1500).start();
                but1_2.animate().alpha(1.0f).setDuration(3000).start();
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2= new Intent(MainActivity.this, AwarenessActivity.class);
                startActivity(int2);
            }

        });

        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3= new Intent(MainActivity.this, DictionaryActivity.class);
                startActivity(int3);
            }
        });

        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4= new Intent(MainActivity.this, LogbookActivity.class);
                startActivity(int4);
            }


        });
    }

    @Override
    public void onStart() {
        super.onStart();
        but1.setVisibility(View.VISIBLE);
        but1_1.setAlpha(0);
        but1_2.setAlpha(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bt:
                Intent int5= new Intent(MainActivity.this, BluetoothSettingActivity.class);
                startActivity(int5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToAltCorrection(View v){
        Intent int1= new Intent(MainActivity.this, AltcorrectionActivity.class);
        startActivity(int1);
    }

    public void goToWeightBalance(View v){
        Intent int1= new Intent(MainActivity.this, WeightAndBalance.class);
        startActivity(int1);
    }
}
