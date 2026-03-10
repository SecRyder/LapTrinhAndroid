package com.ptithcm.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ptithcm.intent.model.SinhVien;

public class ThirdActivity extends AppCompatActivity {
    TextView txtShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        txtShowInfo = findViewById(R.id.txtShowInfo);
        Intent intent = getIntent();
        SinhVien sv = (SinhVien) intent.getSerializableExtra("noidung");
        txtShowInfo.setText("Ho ten sinh vien: " + sv.getHoten() + "\n Nam sinh: " + sv.getNamSinh() + "\n Dia chi: " + sv.getDiaChi());
    }
}
