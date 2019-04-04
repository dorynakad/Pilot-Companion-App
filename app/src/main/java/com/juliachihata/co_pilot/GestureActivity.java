package com.juliachihata.co_pilot;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.UUID;

import static android.os.SystemClock.sleep;

public class GestureActivity extends AppCompatActivity {

    // address == 00:18:E5:03:71:9E
    int red = android.R.color.holo_red_dark;
    int green = android.R.color.holo_green_dark;
    Button test;
    TextView sequenceTextView, resultText, messageText,percText;
    ImageView heart1, heart2, heart3;
    TextView infoTextView;
    TextView secondsTextView;
    TextView roundsTextView;
    TextView startCount, startText;
    ConstraintLayout missionLayout,resultLayout;
    int difficulty;
    double percentage;
    long seconds,tock,time,score;
    int repeat, i, random, life;
    CountDownTimer timer;
    Random rand = new Random();
    String value;
    String address = null;
    int read;
    int cont;
    CountDownTimer countDownTimer;
    Boolean running;
    Timer myTimer;
    TextView lumn;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    boolean check;
    boolean good = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    arrowThread run1;
    Thread at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        address = "00:18:E5:03:71:9E";
        setContentView(R.layout.activity_gesture);

        missionLayout = findViewById(R.id.missionLayout);
        resultLayout = findViewById(R.id.resultLayout);
        sequenceTextView = findViewById(R.id.sqnc_edittext);
        infoTextView = findViewById(R.id.info_edittext);
        secondsTextView = findViewById(R.id.seconds_edittext);
        roundsTextView = findViewById(R.id.rounds_edittext);
        resultText = findViewById(R.id.result);
        messageText = findViewById(R.id.messagetext);
        startCount = findViewById(R.id.startcount);
        startText = findViewById(R.id.starttext);
        percText = findViewById(R.id.perc);
        test = findViewById(R.id.TEST);
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        difficulty = preferences.getInt("difficulty", 1);

        roundsTextView.setText((0) + "/" + (0));

        startCount.setVisibility(View.VISIBLE);
        startText.setVisibility(View.VISIBLE);
        missionLayout.setVisibility(View.INVISIBLE);
        resultLayout.setVisibility(View.INVISIBLE);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        ConnectBT connectBT = new ConnectBT();

        connectBT.execute();
        check = connectBT.ConnectSuccess;

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int g = 0;

                for (int i = 0; i < 35; i++) {

                    sendSignal("1");
                    sleep(100);
                    if (btSocket != null) {
                        try {
                            read = btSocket.getInputStream().read();

                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    if (read == 1) {
                        Toast.makeText(getApplicationContext(), "GOT UP", Toast.LENGTH_LONG).show();
                        g = 1;
                        break;
                    }
                }
                if (g == 0) {
                    Toast.makeText(getApplicationContext(), "RETRY", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void sendSignal(String number) {

        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());

            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    public class ConnectBT extends AsyncTask<Void, Void, Void> {
        boolean ConnectSuccess = false;
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(GestureActivity.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                    ConnectSuccess = true;
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                // msg("Connection Failed. Is it a SPP Bluetooth? Try again.");

                msg("Turn On Sensor ");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                int stop;
                stop = preferences.getInt("stop", 0);
                stop++;
                System.out.println(stop + " ");
                editor.putInt("stop", stop);
                editor.commit();
                if (stop == 4) {
                    msg("Something Went Wrong");
                    Intent int1 = new Intent(GestureActivity.this, MainActivity.class);
                    startActivity(int1);
                    finish();
                }

            } else {
                msg("Connected");
                isBtConnected = true;
                ConnectSuccess = true;
                editor.putBoolean("isConnected", true);
                editor.putInt("stop", 0);
                editor.commit();
                startCount();
            }

            progress.dismiss();
        }
    }

    public void startCount() {
        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                startCount.setText(String.valueOf(1 + (l / 1000)) + "");
            }

            @Override
            public void onFinish() {
                startCount.setVisibility(View.INVISIBLE);
                startText.setVisibility(View.INVISIBLE);
                missionLayout.setVisibility(View.VISIBLE);
                play();

            }
        }.start();
    }

    public void play() {
        i = 0;
        life = 3;
        score = 0;
        running = false;

        if (difficulty == 0) {
            repeat = 8;
            seconds = 10000;
        }

        else if (difficulty == 1) {
            repeat = 11;
            seconds = 6000;
        }

        else if (difficulty == 2) {
            repeat = 15;
            seconds = 5000;

        }

        time = repeat * seconds;

        timer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long l) {
                tock = l;
                     secondsTextView.setText(String.valueOf((tock / 1000)) + "");
            }

            @Override
            public void onFinish() {
                       secondsTextView.setText(0+"");
            }
        }.start();
        run1 = new arrowThread(repeat, seconds, score);
        at = new Thread(run1);

        at.start();

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onStop();
    }

    class arrowThread implements Runnable {

        int repeat;
        long seconds;
        long score;
        int cont;
        double percenta = 0;
        double fin = 0;
        int j;
        long tick;
        int correct = 0;
        double tries = 0;
        int check;
        volatile boolean cancel;

        arrowThread(int repeat, long seconds, long score) {
            this.repeat = repeat;
            this.seconds = seconds;
            this.score = score;
            cancel = false;
        }

        public void cancel() {
            cancel = true;
        }

        @Override
        public void run() {
            for (j = 0; j < repeat; j++) {
                check = 0;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        random = rand.nextInt(4) + 1;
                        cont = (int) seconds / 200;
                        tries++;
                        roundsTextView.setText((correct) + "/" + (repeat));
                        if (random == 1) {
                            good = false;
                            sequenceTextView.setText("↑");
                            new CountDownTimer(seconds - 300, 300) {

                                @Override
                                public void onTick(long l) {
                                    tick = l;
                                    if (check == 0) {
                                        sendSignal("1");
                                        System.out.println("sending signal");

                                        if (btSocket != null) {
                                            try {
                                                read = btSocket.getInputStream().read();

                                            } catch (IOException e) {
                                                msg("Error");
                                            }
                                        }
                                        if (read == 1) {
                                            System.out.println("GOT IT");
                                            good = true;
                                            correct++;
                                            roundsTextView.setText((correct) + "/" + (repeat));
                                            percenta = correct/tries;
                                            percText.setText(String.format( "%.2f", percenta*100 ));
                                            check = 1;
                                            if (good == true) {
                                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                        if (good == false) {
                                            percenta = correct/tries;
                                            percText.setText(String.format( "%.2f", percenta*100 ));
                                            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                                        }

                                }
                            }.start();

                        }
                        else if (random == 2) {
                            good = false;
                            sequenceTextView.setText("↓");
                            new CountDownTimer(seconds - 300, 300) {

                                @Override
                                public void onTick(long l) {
                                    tick = l;
                                    if (check == 0) {
                                        sendSignal("2");
                                        System.out.println("sending signal");

                                        if (btSocket != null) {
                                            try {
                                                read = btSocket.getInputStream().read();

                                            } catch (IOException e) {
                                                msg("Error");
                                            }
                                        }
                                        if (read == 2) {
                                            System.out.println("GOT IT");
                                            good = true;
                                            correct++;
                                            roundsTextView.setText((correct) + "/" + (repeat));
                                            percenta = correct/tries;
                                            percText.setText(String.format( "%.2f", percenta*100 ));
                                            check = 1;
                                            if (good == true) {
                                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (good == false) {
                                        percenta = correct/tries;
                                        percText.setText(String.format( "%.2f", percenta*100 ));
                                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                                    }

                                }
                            }.start();

                        }

                        else if (random == 3) {
                            good = false;
                            sequenceTextView.setText("←");
                            new CountDownTimer(seconds - 300, 300) {

                                @Override
                                public void onTick(long l) {
                                    tick = l;
                                    if (check == 0) {
                                        sendSignal("3");
                                        System.out.println("sending signal");
                                        if (btSocket != null) {
                                            try {
                                                read = btSocket.getInputStream().read();

                                            } catch (IOException e) {
                                                msg("Error");
                                            }
                                        }
                                        if (read == 3) {
                                            System.out.println("GOT IT");
                                            good = true;
                                            correct++;
                                            roundsTextView.setText((correct) + "/" + (repeat));
                                            percenta = correct/tries;
                                            percText.setText(String.format( "%.2f", percenta*100 ));
                                            check = 1;
                                            if (good == true) {
                                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (good == false) {
                                        percenta = correct/tries;
                                        percText.setText(String.format( "%.2f", percenta*100 ));
                                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                                    }

                                }
                            }.start();

                        }

                        else if (random == 4) {
                            good = false;
                            sequenceTextView.setText("→");
                            new CountDownTimer(seconds - 300, 300) {

                                @Override
                                public void onTick(long l) {
                                    tick = l;
                                    if (check == 0) {
                                        sendSignal("4");
                                        System.out.println("sending signal");
                                        if (btSocket != null) {
                                            try {
                                                read = btSocket.getInputStream().read();

                                            } catch (IOException e) {
                                                msg("Error");
                                            }
                                        }
                                        if (read == 4) {
                                            System.out.println("GOT IT");
                                            good = true;
                                            correct++;
                                            roundsTextView.setText((correct) + "/" + (repeat));
                                            percenta = correct/tries;
                                            percText.setText(String.format( "%.2f", percenta*100 ));
                                            check = 1;
                                            if (good == true) {
                                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG)
                                                        .show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFinish() {
                                    if (good == false) {
                                        percenta = correct/tries;
                                        percText.setText(String.format( "%.2f", percenta*100 ));
                                        Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                                    }

                                }
                            }.start();

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
                    Thread.sleep(seconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    System.out.println(percenta*100);
                    fin = percenta*100;
                    System.out.println( String.format( "%.2f", fin ) );

                    if(fin > 60.0){
                        resultText.setText("Passed\nYou Are "+ String.format( "%.2f", fin ) + "% Aware" );
                        messageText.setText("Good Job! Keep Flying Safely");
                        resultText.setTextColor(getResources().getColor(green));
                    }
                    else {
                        resultText.setText("Failed\nYou Are "+ String.format( "%.2f", fin ) + "% Aware" );
                        messageText.setText("Be Careful! Alerting The ATC");
                        resultText.setTextColor(getResources().getColor(red));
                    }

                    resultLayout.setVisibility(View.VISIBLE);
                    missionLayout.setVisibility(View.INVISIBLE);
                    sleep(500);
                }
            });

            sleep(5000);

            onStop();

        }

    }

}
