package com.juliachihata.co_pilot;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.UUID;

import static android.os.SystemClock.sleep;

public class BluetoothActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btnDis, btnReturn;
    String value;
    String address = null;
    int read;

    TextView lumn;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(BluetoothSettingActivity.EXTRA_ADDRESS);


        new ConnectBT().execute();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                for(int i =0; i< 25; i++){

                    sendSignal("1");
                    sleep(200);
                    if ( btSocket != null ) {
                        try {
                            read = btSocket.getInputStream().read();


                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    if(read == 1){
                        Toast.makeText(getApplicationContext(), "GOT UP", Toast.LENGTH_LONG).show();
                        break;
                    }
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                for(int i =0; i< 25; i++){

                    sendSignal("2");
                    sleep(200);
                    if ( btSocket != null ) {
                        try {
                            read = btSocket.getInputStream().read();


                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    if(read == 2){
                        Toast.makeText(getApplicationContext(), "GOT DOWN", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                for(int i =0; i< 25; i++){

                    sendSignal("3");
                    sleep(200);
                    if ( btSocket != null ) {
                        try {
                            read = btSocket.getInputStream().read();


                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    if(read == 3){
                        Toast.makeText(getApplicationContext(), "GOT LEFT", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                for(int i =0; i< 25; i++){

                    sendSignal("4");
                    sleep(200);
                    if ( btSocket != null ) {
                        try {
                            read = btSocket.getInputStream().read();


                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    if(read == 4){
                        Toast.makeText(getApplicationContext(), "GOT RIGHT", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        });


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent int5= new Intent(BluetoothActivity.this, MainActivity.class);
                startActivity(int5);
            }
        });


        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Disconnect();
            }
        });
    }

    private void sendSignal ( String number ) {

        if ( btSocket != null ) {
            try {
                btSocket.getOutputStream().write(number.toString().getBytes());


            } catch (IOException e) {
                msg("Error");
            }
        }
    }

    private void Disconnect () {
        if ( btSocket!=null ) {
            try {
                btSocket.close();
            } catch(IOException e) {
                msg("Error");
            }
        }

        finish();
    }

    private void msg (String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    public class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(BluetoothActivity.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground (Void... devices) {
            try {
                if ( btSocket==null || !isBtConnected ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected");
                isBtConnected = true;
            }

            progress.dismiss();
        }
    }

}
