package com.ptithcm.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView txtShowText, txtArrayShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        txtShowText = findViewById(R.id.txtShowText);
        txtArrayShow = findViewById(R.id.txtArrayShow);
        Intent intent = getIntent();
        String noidung = intent.getStringExtra("noidung");
        String[] noidungArray = intent.getStringArrayExtra("noidungArray");
        txtShowText.setText(noidung);
        txtArrayShow.setText(noidungArray[1].toString());
    }
}
