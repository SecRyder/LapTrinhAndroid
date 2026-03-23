package com.ptithcm.internalexternalstogratenew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View

        .OnClickListener {
    Button btnwriteIS, btnreadIS, btnwriteFC, btnreadFC, btnwriteES, btnreadES;
    ImageView imageView2;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnwriteIS).setOnClickListener(this);
        findViewById(R.id.btnreadIS).setOnClickListener(this);
        findViewById(R.id.btnwriteFC).setOnClickListener(this);
        findViewById(R.id.btnreadFC).setOnClickListener(this);
        findViewById(R.id.btnwriteES).setOnClickListener(this);
        findViewById(R.id.btnreadES).setOnClickListener(this);
        imageView2 = findViewById(R.id.imageView2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnreadIS:
                String readDatatIS = readIS("myfile.txt");
                Toast.makeText(this, "Noi dung file" + readDatatIS, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnwriteIS:
                writeIS("myfile.text", "Xin chao cac ban!");
                break;
            case R.id.btnreadFC:
                String readDataFC = readFC("mycached.cache");
                Toast.makeText(this, "Data caches: " + readDataFC, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnwriteFC:
                writeFC("mycached.cache", "Du lieu luu vao cache");
                break;
            case R.id.btnwriteES:
                writeES("mysdcard.txt", "Du lieu luu vao the nho ngoai");
                break;
            case R.id.btnreadES:
                String readDataEX = readES("mysdcard.txt");
                Toast.makeText(this, "Data sdcard: " + readDataEX, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String readES(String filename) {
        String sdcard = getExternalFilesDir("") + "/" + filename;
        try {
            Scanner scan = new Scanner(new File(sdcard));
            String data = "";
            while (scan.hasNext()) {
                data = data + scan.nextLine() + "\n";
            }
            scan.close();
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void writeES(String filename, String data) {
        String sdcard = getExternalFilesDir("") + "/" + filename;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
            writer.write(data);
            writer.close();
            Toast.makeText(this, "Luu thanh cong", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeFC(String filename, String data) {
        File cacheDir = getCacheDir();
        try (FileOutputStream fout = new FileOutputStream(new File(cacheDir, filename))) {
            fout.write(data.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Du lieu luu vao cach thanh cong!", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String readFC(String filename) {
        File cacheDir = getCacheDir();
        byte[] buffer = new byte[1024];
        int length = 0;
        try (FileInputStream fin = new FileInputStream(new File(cacheDir, filename))) {
            length = fin.read(buffer);
            return new String(buffer, 0, length);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Errror: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void writeIS(String filename, String data) {
        try {
            FileOutputStream fout = openFileOutput(filename, MODE_PRIVATE);
            fout.write(data.getBytes(StandardCharsets.UTF_8));
            fout.close();
            Toast.makeText(this, "Du lieu da duoc ghi", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    private String readIS(String filename) {
        try (FileInputStream fin = openFileInput(filename)) {
            byte[] buffer = new byte[1024];
            int length = 0;
            length = fin.read(buffer);
            return new String(buffer, 0, length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}