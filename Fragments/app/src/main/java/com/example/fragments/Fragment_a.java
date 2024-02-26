package com.example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_a extends Fragment {

    public Fragment_a() {
        // Required empty public constructor
    }
public static final String ARG1="argument1";
    public static final String ARG2="argument2";

    public static Fragment_a getInstance(String value1,int value2){
        Fragment_a fragment=new Fragment_a();

        Bundle bundle=new Bundle();
        bundle.putString(ARG1,value1);
        bundle.putInt(ARG2,value2);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        TextView txtfrag1 = view.findViewById(R.id.txtfrag1);

        if (getArguments() != null) {
            String name = getArguments().getString(ARG1);
            int number = getArguments().getInt(ARG2, 0);
            txtfrag1.setText(name + " " + number);

            ((MainActivity)getActivity()).fromFragment();
        }
        return view;
    }
}