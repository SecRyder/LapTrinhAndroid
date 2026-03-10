package com.ptithcm.gridviewnangcaoslide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.ptithcm.gridviewnangcaoslide.adapter.HinhAnhAdapter;
import com.ptithcm.gridviewnangcaoslide.model.HinhAnh;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<HinhAnh> arrayHinhAnh;
    GridView gridView;
    HinhAnhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gvHinhanh);
        arrayHinhAnh = new ArrayList<>();
        arrayHinhAnh.add(new HinhAnh(R.drawable.beach, "Hinh số 1"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.chuctet, "Hinh số 2"));

        arrayHinhAnh.add(new HinhAnh(R.drawable.hill, "Hinh số 3"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.lake, "Hinh số 4"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.mamco, "Hinh số 5"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.mountain, "Hinh số 6"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.noel, "Hinh số 7"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.river, "Hinh số 8"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.trunhthu, "Hinh số 9"));
        adapter = new
                HinhAnhAdapter(this, R.layout.dong_hinh_anh, arrayHinhAnh);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                HinhAnh hinhAnh = arrayHinhAnh.get(i);
                Intent intent = new Intent(MainActivity.this,
                        PictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ha", hinhAnh);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }
}