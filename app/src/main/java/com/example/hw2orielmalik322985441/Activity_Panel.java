package com.example.hw2orielmalik322985441;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_Panel extends AppCompatActivity {

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;
    private ArrayList<Integer>Records;
    private ListView listView;
 Intent scoreIntent;
Bundle scoreBundle;


    CallBack_UserProtocol callBack_userProtocol = new CallBack_UserProtocol() {
        @Override
        public void user(String user) {
            showUserLocation(user);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);
        Records=new ArrayList<Integer>();
        fragment_list = new Fragment_List();
        listView=new ListView(this);

        this.listView=(ListView) findViewById(R.id.mobile_list);

      //  fragment_list.setRecords(Records);
       // fragment_list.ListToView( fragment_list.getContext());
        if(null!=getIntent().getBundleExtra("Scoreintent")&&  fragment_list.getRecords().size()>0){
            fragment_list.getRecords().add((getIntent().getBundleExtra("Scoreintent").getInt("Score")));
        }

        fragment_map = new Fragment_Map();




        fragment_list.setCallBack_userProtocol(callBack_userProtocol);

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, fragment_list).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_map, fragment_map).commit();





        //fragment_list.setListView(listView);



    }


    private void showUserLocation(String user) {
        double lat = 30.99;
        double lon = 32.67;
        fragment_map.zoom(lat, lon);
    }

    public void setRecords(ArrayList<Integer> records) {
        Records = records;
    }

    public ArrayList<Integer> getRecords() {
        return Records;
    }











}

