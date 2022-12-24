package com.example.hw2orielmalik322985441;

import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Fragment_Map extends Fragment {

        private MaterialTextView map_LBL_title;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_map, container, false);

            findViews(view);
            initViews();



            return view;
        }

        public void zoom(double lat, double lon) {
            map_LBL_title.setText(lat + "\n" + lon);
        }

        private void initViews() {

        }

        private void findViews(View view) {
            map_LBL_title = view.findViewById(R.id.map_LBL_title);
        }
    }


