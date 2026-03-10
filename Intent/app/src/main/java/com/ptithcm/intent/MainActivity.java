package com.ptithcm.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ptithcm.intent.model.SinhVien;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int[] listButtonID = {
            R.id.btnSendData,
            R.id.btnShowWeb,
            R.id.btnCallSomone,
            R.id.btnEditContact,
            R.id.btnViewContact,
            R.id.btnSendMassage,
            R.id.btnSendData2,
            R.id.btnSendData3
    };

    ImageView imgChrome, imgSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imgChrome = findViewById(R.id.imgChrome);
        imgSMS = findViewById(R.id.imgSMS);
        imgChrome.setOnClickListener(this);
        imgSMS.setOnClickListener(this);
        init();
    }

    public void init() {
        for (int id : listButtonID) {
            Button btnTemp = (Button) findViewById(id);
            btnTemp.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int id = view.getId();
        if (id == R.id.btnSendData) {
            intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("noidung", "Tran Thi Anh Nguyet");
            String[] arrayCourse = {"Android", "PHP", "C++"};
            intent.putExtra("noidungArray", arrayCourse);
        } else if (id == R.id.btnShowWeb) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://vnexpress.net"));
        } else if (id == R.id.btnCallSomone) {
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:098767876"));
        } else if (id == R.id.btnSendMassage) {
            intent.setAction(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("sms:098767876"));
            intent.putExtra("sms_body", "Xin chao");
        } else if (id == R.id.btnViewContact) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("content://contacts/people/"));
        } else if (id == R.id.btnEditContact) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("content://contacts/people/1"));
        } else if (id == R.id.btnSendData2) {
            intent = new Intent(MainActivity.this, ThirdActivity.class);
            SinhVien sv = new SinhVien("Tran Thi Anh Nguyet", 2004, "97 Man Thien");
            intent.putExtra("noidung", sv);
        } else if (id == R.id.btnSendData3) {
            intent = new Intent(MainActivity.this, FourthActivity.class);
            String[] arrayCountry = {"Ha Noi", "Da Nang", "TP Ho Chi Minh"};
            SinhVien sv = new SinhVien("Tran Thi Anh Nguyet", 2004, "97, Man Thien");
            Bundle bundle = new Bundle();
            bundle.putString("chuoi", "Lop lap trinh thiet bi di dong");
            bundle.putInt("kieuso", 1234);
            bundle.putStringArray("kieuMang", arrayCountry);
            bundle.putSerializable("kieuObject", sv);
            intent.putExtra("noidung", bundle);
        } else if (id == R.id.imgChrome) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://tuoitre.vn"));
        } else if (id == R.id.imgSMS) {
            intent.setAction(Intent.ACTION_SENDTO);
            intent.putExtra("sms_body", "Lop hoc hom nay tot");
            intent.setData(Uri.parse("sms:098767876"));
        }
        startActivity(intent);
    }
}