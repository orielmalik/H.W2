package com.example.hw2orielmalik322985441;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Fragment_List extends Fragment  {

    private MaterialButton list_BTN_user1;
    private CallBack_UserProtocol callBack_userProtocol;
    private ListView listView;
    public ArrayList<Integer> Records;
    private Context context;
    private MaterialTextView[]places;

    public void setCallBack_userProtocol(CallBack_UserProtocol callBack_userProtocol) {
        this.callBack_userProtocol = callBack_userProtocol;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        //  this.listView = new ListView(view.getContext());
        this.Records = new ArrayList<Integer>();
//Records.add(getActivity().getIntent().getBundleExtra("Scorebundle").getInt("Score"));
        places=new MaterialTextView[10];
        findViews(view);
        initViews();
        places = new MaterialTextView[]{
                view.findViewById(R.id.place1), view.findViewById(R.id.place2),view.findViewById(R.id.place3),view.findViewById(R.id.place4),view.findViewById(R.id.place5),

                view.findViewById(R.id.place6), view.findViewById(R.id.place7),view.findViewById(R.id.place8),view.findViewById(R.id.place9),view.findViewById(R.id.place10)};





        changeTitle("List Page");
        Edit(Records);


//ListToView(view);

        return view;
    }


    private void user1Clicked() {
        if (callBack_userProtocol != null) {
            callBack_userProtocol.user("Oriel");
        }
    }

    public void changeTitle(String title) {
    }

    private void initViews() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user1Clicked();
            }
        };

        list_BTN_user1.setOnClickListener(onClickListener);
    }

    private void findViews(View view) {
        list_BTN_user1 = view.findViewById(R.id.list_BTN_user1);
        //listView = view.findViewById(R.id.mobile_list);
    }


    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setRecords(ArrayList<Integer> records) {
        Records = records;
    }

    public ArrayList<Integer> getRecords() {
        return Records;
    }

    public void ListToView(View v) {
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(
                v.getContext(),
                android.R.layout.simple_list_item_1,
                Records);
        listView.setAdapter(arrayAdapter);
    }


    private  void Edit(ArrayList<Integer>a)
    {
        if(a.size()>0)
            for (int i = 0; i < places.length; i++) {
                //places[i].setVisibility(View.VISIBLE);

                this.places[i].setText(String.valueOf(a.get(i)));
                Log.d( "Edit: ",""+a.get(i));

                // Log.d( "Edits: ",""+this.places[i].getText());;

            }
        //this.places[2].setText("222");
    }

}
