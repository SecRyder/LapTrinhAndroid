package com.ptithcm.demotoast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


// Ham Main
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnMethod2;
    Button btnMethod3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Method 2
        btnMethod2 = findViewById(R.id.btnMethod2);
        btnMethod2.setOnClickListener(this);

        // Method 3
        btnMethod3 = findViewById(R.id.btnMethod3);
        btnMethod3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this, "This is a Toast Method 3", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Ham showToast Method 1
    public void showToast(View view){
        Toast.makeText(this, "This is a Toast Method1", Toast.LENGTH_SHORT).show();
    }

    // Ham cho Method 2
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnMethod2){
            Toast.makeText(this, "This is a Toast Method 2", Toast.LENGTH_SHORT).show();
        }
    }
}