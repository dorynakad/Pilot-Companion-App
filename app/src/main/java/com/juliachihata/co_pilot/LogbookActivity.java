package com.juliachihata.co_pilot;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogbookActivity extends AppCompatActivity {

    HashSet<String> emptySet;  //Creating an empty set so that the set would be "empty" and not "invalid" in case it is
    ListView lv;
    EditText timeTxt;
    EditText timeTxt2;
    Button  addBtn,updateBtn,clearBtn,deleteBtn;
    ArrayList<String> flightTimes;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;

//public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
     //   SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
     //   flightTimes = settings.getString("flightTimes",flightTimes);

        flightTimes = new ArrayList<String>();
        sharedPreferences = getSharedPreferences("mySharedPrefs", MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=findViewById(R.id.listView);
        timeTxt =  findViewById(R.id.timeTxt);
      //  timeTxt2 = findViewById(R.id.timeTxt2);
        addBtn  =  findViewById(R.id.addBtn);
        updateBtn   =   findViewById(R.id.updateBtn);
        clearBtn    =   findViewById(R.id.cleanBtn);
        deleteBtn   =   findViewById(R.id.deleteBtn);

        //Adapter

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice , flightTimes);
        lv.setAdapter(adapter);

        //Set selected item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

                timeTxt.setText(flightTimes.get(pos));


            }
        });
    //Handle events
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
                saveEntries();


            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                saveEntries();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
                saveEntries();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogbookActivity.this);
                builder.setCancelable(true);
                builder.setTitle("ALERT");
                builder.setMessage("By pressing OK you will CLEAR all your logbook activity, this is a permanent action");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clear();
                    }
                });
                builder.show();
            }


        });


        emptySet = new HashSet<String>();
        emptySet.add("");
        loadEntries();

    }

    public String[] splitString(String s) {
        return s.split(" ");
    }


    public void saveEntries() { //So that we can save the logbook on the phone (Hopefully on the clouds later
        HashSet<String> set = new HashSet<String>(flightTimes);
        sharedPreferences.edit().putStringSet("entries", set).apply();
    }

    public void loadEntries() { //Loading the logbook even if reinstalled and also loading an empty set in case it is empty
        Set<String> set = sharedPreferences.getStringSet("entries", null);
        if(set==null) {
            set = emptySet;
        } else {
            populateList(set);
        }

        adapter.notifyDataSetChanged();

    }

    public void populateList(Set<String> set) {
        for(String s : set) {
            flightTimes.add(s);
        }
    }

    Date currentTime = Calendar.getInstance().getTime();
    //Add

    private void add(){

       String flightTime=timeTxt.getText().toString() ;//+ timeTxt2.getText().toString();

      //  int timeInt = timeTxt.getString();

        if(!flightTime.isEmpty() && flightTime.length()>0){
            //Add
            adapter.add(currentTime+"    "+flightTime+" Hours");
            //Refresh
            adapter.notifyDataSetChanged();

            timeTxt.setText("");

           Toast.makeText(getApplicationContext(),"Added" +timeTxt, Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(),"Nothing to add" +timeTxt, Toast.LENGTH_LONG).show();
        }
    }

    //Update

    private void update(){
        String flightTime=timeTxt.getText().toString();

        //GET POS OF SELECTED ITEM
        int pos=lv.getCheckedItemPosition();
        if(!flightTime.isEmpty() && flightTime.length()>0){

            //Remove item
            adapter.remove(flightTimes.get(pos));

            //Insert

            adapter.insert(flightTime,pos);

            //refresh
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"Updated" +timeTxt, Toast.LENGTH_SHORT).show();}
            else {
            Toast.makeText(getApplicationContext(), " Nothing to update" , Toast.LENGTH_SHORT).show();
        }
        saveEntries();

        

        }

        //delete
    private void delete(){

        int pos=lv.getCheckedItemPosition();
        if(pos > -1)
        {
                //remove
            adapter.remove(flightTimes.get(pos));
            //refresh
            adapter.notifyDataSetChanged();

            timeTxt.setText("");
            Toast.makeText(getApplicationContext(), "Delete" , Toast.LENGTH_SHORT).show();}
            else{
            Toast.makeText(getApplicationContext(), "Nothing to delete" , Toast.LENGTH_SHORT).show();

        }

        

    }

    //clear
    private void clear(){
    adapter.clear();
    saveEntries();
    }

}
