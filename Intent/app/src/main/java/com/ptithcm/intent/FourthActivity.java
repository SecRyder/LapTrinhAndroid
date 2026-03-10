package com.ptithcm.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ptithcm.intent.model.SinhVien;

public class FourthActivity extends AppCompatActivity {
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);
        txtInfo = findViewById(R.id.txtInfo);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("noidung");
        if (bundle != null) {
            String chuoi = bundle.getString("chuoi");
            int kieuso = bundle.getInt("kieuso", 123);
            String[] kieuMang = bundle.getStringArray("kieuMang");
            SinhVien sv = (SinhVien) bundle.getSerializable("kieuObject");
            txtInfo.setText(chuoi + "\n" + kieuso + "\n" + kieuMang[2] + "\n" + sv.getHoten());
        }
    }

}
