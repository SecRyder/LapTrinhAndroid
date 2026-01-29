package com.ptithcm.listviewnangcao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ptithcm.listviewnangcao.adapter.CityAdapter;
import com.ptithcm.listviewnangcao.model.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ListView lstView;
    ArrayList<City> arrayListCity;
    CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        lstView = findViewById(R.id.lstView);

        arrayListCity = new ArrayList<>();
        arrayListCity.add(new City("London", R.drawable.london, "https://en.wikipedia.org/wiki/London"));
        arrayListCity.add(new City("Rome", R.drawable.rome, "https://en.wikipedia.org/wiki/Rome"));
        arrayListCity.add(new City("Paris", R.drawable.paris, "https://en.wikipedia.org/wiki/Paris"));

        cityAdapter = new CityAdapter(this, R.layout.layout_dong, arrayListCity);
        lstView.setAdapter(cityAdapter);

        // Khi nhan vao 1 dong trong List View thi mo link hinh tuong ung
        lstView.setOnItemClickListener((parent, view, position, id) -> {
            City city = arrayListCity.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(city.getLinkHinh()));
            startActivity(intent);
        });
    }
}