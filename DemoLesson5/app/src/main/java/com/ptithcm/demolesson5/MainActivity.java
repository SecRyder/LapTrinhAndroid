package com.ptithcm.demolesson5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgView;
    EditText edtUsername, edtPassword;
    CheckBox ckRememberPassword;
    Button btnLogin, btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.imgView);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        ckRememberPassword = findViewById(R.id.ckRememberPassword);
        // Method 2: btn.setOnClickListener(this); + onClick()
        btnLogin = findViewById(R.id.btnLogin);
        btnThoat = findViewById(R.id.btnThoat);
        btnLogin.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        edtUsername.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) edtUsername.setText("");
                }
        );

        edtPassword.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) edtPassword.setText("");
                }
        );
        // Kiem tra co auto login khi mo app
        if (readAutoLogin()) {
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                handleLogin();
                break;
            case R.id.btnThoat:
                finish(); // dong app
                break;
        }
    }

    // Luu thong tin khi dang nhap
    private void saveAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.commit();
    }

    // Tu dong dang nhap: Kiem tra xem co du lieu truoc do khong, neu co thi tu dang nhap
    private boolean readAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        String username = preferences.getString("username", "");
        if (username != null && !username.equals("")) {
            Intent intent = new Intent(this, LoginSucess.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    // Xoa thong tin dang nhap
    private void clearAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    // Ham xu ly chuoi dang nhap
    private void handleLogin() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // Xac thuc dau vao
        if (username.isEmpty()) {
            edtUsername.setError("Vui long nhap username");
            return;
        }
        if (password.isEmpty()) {
            edtPassword.setError("Vui long nhap password");
            return;
        }

        // Kiem tra thong tin dang nhap
        if (username.equals("nguyet") && password.equals("21042004")) {
            // Kiem tra xem da tick remember pass chua
            if (ckRememberPassword.isChecked())
                saveAutoLogin();
            else clearAutoLogin();

            // Chuyen sang MainActivity
            Intent intentLog = new Intent(this, LoginSucess.class);
            startActivity(intentLog);
            finish();
        } else {
            edtUsername.setError("Sai username hoac password");
            // edtUsername.setText("");
            // edtPassword.setText("");
        }
    }
}
