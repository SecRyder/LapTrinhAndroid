package com.ptithcm.fragments;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements FragmentB.Counter {
    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void incrementValue(int count) {
        FragmentB fragmentB;
        fragmentB = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragmentB);
        if (fragmentB != null) {
            fragmentB.setTheCount(count);
        }
    }

//    public void incrementValue(int count) {
//        FragmentB fragmentB;
//        fragmentB = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragmentB);
//        if (fragmentB != null) {
//            fragmentB.setTheCount(count);
//        }
//    }


}