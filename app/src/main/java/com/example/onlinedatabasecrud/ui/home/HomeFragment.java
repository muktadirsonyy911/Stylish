package com.example.onlinedatabasecrud.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinedatabasecrud.BeautyParlour;
import com.example.onlinedatabasecrud.DashBoard;
import com.example.onlinedatabasecrud.MapsActivity;
import com.example.onlinedatabasecrud.Nail;
import com.example.onlinedatabasecrud.R;
import com.example.onlinedatabasecrud.RecycleView;
import com.example.onlinedatabasecrud.Spa;
import com.example.onlinedatabasecrud.SpinnerActivity;
import com.example.onlinedatabasecrud.TattooShops;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final Button btn1 = root.findViewById(R.id.SaloonBTN);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RecycleView.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        final Button btn2 = root.findViewById(R.id.ParlourBTN);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BeautyParlour.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        final Button btn3 = root.findViewById(R.id.TattooBTN);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TattooShops.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        final Button btn4 = root.findViewById(R.id.SpaBTN);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Spa.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        final Button btn5 = root.findViewById(R.id.NailBTN);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Nail.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                btn1.setText(s);
                btn2.setText(s);
                btn3.setText(s);
                btn4.setText(s);
                btn5.setText(s);
            }
        });
        return root;
    }
}