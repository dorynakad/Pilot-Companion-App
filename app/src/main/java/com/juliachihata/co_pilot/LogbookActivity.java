package com.juliachihata.co_pilot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogbookActivity extends AppCompatActivity {

    ListView lv;
    EditText timeTxt;
    Button  addBtn,updateBtn,clearBtn,deleteBtn;
    ArrayList<String> flightTimes = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=findViewById(R.id.listView);
        timeTxt =  findViewById(R.id.timeTxt);
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
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

    }

    //Add

    private void add(){
        String flightTime=timeTxt.getText().toString();

        if(!flightTime.isEmpty() && flightTime.length()>0){
            //Add
            adapter.add(flightTime);
            //Refresh
            adapter.notifyDataSetChanged();

            timeTxt.setText("");

            Toast.makeText(getApplicationContext(),"Added" +timeTxt, Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(),"!! Nothing to add" +timeTxt, Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), "!! Nothing to update" , Toast.LENGTH_SHORT).show();
        }



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
            Toast.makeText(getApplicationContext(), "!! Nothing to delete" , Toast.LENGTH_SHORT).show();

        }


    }

    //clear
    private void clear(){
    adapter.clear();

    }
}
