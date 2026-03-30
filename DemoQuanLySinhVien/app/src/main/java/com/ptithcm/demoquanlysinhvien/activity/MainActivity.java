package com.ptithcm.demoquanlysinhvien.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ptithcm.demoquanlysinhvien.R;

public class MainActivity extends AppCompatActivity implements View

        .OnClickListener {
    ImageView imageView;
    Button btnDanhMucLopHoc, btnQuanLySinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnDanhMucLopHoc).setOnClickListener(this);
        findViewById(R.id.btnQuanLySinhVien).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDanhMucLopHoc:
                Intent intent = new Intent(MainActivity.this, DanhMucLopHocActivity.class);
                startActivity(intent);
                break;
            case R.id.btnQuanLySinhVien:
                Intent intent1 = new Intent(MainActivity.this, QuanLySinhVienActivity.class);
                startActivity(intent1);
                break;
        }
    }
}