package com.juliachihata.co_pilot;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogbookActivity extends AppCompatActivity {

    HashSet<String> emptySet;  //Creating an empty set so that the set would be "empty" and not "invalid" in case it is
    ListView lv;
    int cPos=-1;
    TextView date_view;
    EditText timeTxt, timeTxt2;
    Button  addBtn,updateBtn,clearBtn,deleteBtn;
    ArrayList<String> flightTimes;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;

    private int day, month, year;
//    int white = android.R.color.white;

    //the two lines below should let the user input numbers from 1 to 12 , the code is in InputFilterMinMax

//    EditText et = (EditText) findViewById(R.id.timeTxt);

//et.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});

    //public
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        flightTimes = new ArrayList<String>();
        sharedPreferences = getSharedPreferences("mySharedPrefs", MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=findViewById(R.id.listView);
        timeTxt =  findViewById(R.id.timeTxt);
        date_view = (TextView) findViewById(R.id.set_date);
        //  timeTxt2 = findViewById(R.id.timeTxt2);
        addBtn  =  findViewById(R.id.addBtn);
        updateBtn   =   findViewById(R.id.updateBtn);
        clearBtn    =   findViewById(R.id.cleanBtn);
        deleteBtn   =   findViewById(R.id.deleteBtn);

        //to limit characters
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(5);
        timeTxt.setFilters(FilterArray);

        //Adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, flightTimes){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView ListItemShow = (TextView) view.findViewById(android.R.id.text1);
                ListItemShow.setTextColor(Color.parseColor("white"));
                return view;
            }
        };

        lv.setAdapter(adapter);
        //Set selected item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {

                // This is to match the content of the input added/ to be updated
                Pattern p_t = Pattern.compile("^*(\\d|\\d.\\d*)(?= Hours)");
                Pattern p_d = Pattern.compile("(?<=\\[).*?(?=\\])");
                Matcher m_t = p_t.matcher(flightTimes.get(pos));
                Matcher m_d = p_d.matcher(flightTimes.get(pos));
                while (m_t.find()) {
                    timeTxt.setText(m_t.group());
                    break;
                }
                while (m_d.find()) {
                    date_view.setText(m_d.group());
                    break;
                }
            }
        });


       //  int cPos=-1;
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(cPos == position){
                    if(lv.isItemChecked(cPos)) {
                        lv.setItemChecked(position, false);
                    }
                    else{
                        lv.setItemChecked(position,true);
                    }
                }
                else {
                    lv.setItemChecked(position,true);
                }
                cPos=lv.getCheckedItemPosition();
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



    //Add
    private void add(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' HH:mm:ss");
        String cTime = simpleDateFormat.format(currentTime);

        String flightTime=timeTxt.getText().toString();
        String dateLog = date_view.getText().toString();

        if(!flightTime.isEmpty() && flightTime.length()>0){
            if (dateLog.isEmpty())
                Toast.makeText(getApplicationContext(),"Please choose a date", Toast.LENGTH_LONG).show();
            else if (Double.parseDouble(flightTime) < 0.25 || Double.parseDouble(flightTime) > 5){
                Toast.makeText(getApplicationContext(),"Error!\nMin time: 0.25 h\nMax time: 5 h", Toast.LENGTH_LONG).show();
            }
            else {
                //Add
                if (!dateLog.isEmpty()) {
                    adapter.add(cTime + "\n[" + dateLog + "]    " + flightTime + " Hours");
                    saveEntries();
                }

                //Refresh
                adapter.notifyDataSetChanged();

                //clear contexts
                timeTxt.setText("");
                date_view.setText("");

                Toast.makeText(getApplicationContext(),"Successfully Added!" , Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Nothing to add", Toast.LENGTH_LONG).show();
        }
    }

    //Update
    private void update(){
        String flightTime=timeTxt.getText().toString();
        String dateLog = date_view.getText().toString();

        //GET POS OF SELECTED ITEM
        int pos=lv.getCheckedItemPosition();

        if(!flightTime.isEmpty() && flightTime.length()>0) {
            if (dateLog.isEmpty())
                Toast.makeText(getApplicationContext(), "Please choose a date", Toast.LENGTH_LONG).show();
            else if (Double.parseDouble(flightTime) < 0 || Double.parseDouble(flightTime) > 5) {
                Toast.makeText(getApplicationContext(), "Error!\nMin time: 0.25 h\nMax time: 5 h", Toast.LENGTH_LONG).show();
            } else {
                String save_record = flightTimes.get(pos);

                //Remove item
                adapter.remove(flightTimes.get(pos));

                String new_record = save_record.replaceAll("^*(\\d|\\d.\\d*)(?= Hours)", flightTime);
               // new_record = new_record.replaceAll("(?<=\\[).*?(?=\\])", dateLog);

                //Insert
                adapter.insert(new_record, pos);

                //refresh
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Nothing to update" , Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Successfully Deleted!" , Toast.LENGTH_SHORT).show();}
        else{
            Toast.makeText(getApplicationContext(), "Nothing to delete" , Toast.LENGTH_SHORT).show();

        }
    }

    //clear
    private void clear(){
        adapter.clear();
        saveEntries();
    }

    public void setDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        DatePickerDialog dpd = new DatePickerDialog(LogbookActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                date_view.setText(m + " / " + d + ", " + y);
            }
        }, day, month, year);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.updateDate(year,month,day);
        dpd.show();
    }

    public void editableText(View view){
        timeTxt.setFocusableInTouchMode(true);
    }

}
