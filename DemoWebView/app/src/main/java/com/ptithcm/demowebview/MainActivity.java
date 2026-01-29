package com.ptithcm.demowebview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
    }

    // Ham cho button Back
    public void backButton(View view){
        if(webView.canGoBack()){
            webView.goBack();
        }
    }

    // Ham cho button forward
    public void forwardButton(View view){
        if(webView.canGoForward()){
            webView.goForward();
        }
    }

    // Ham cho button reload
    public void reloadButton(View view){
        webView.reload();
    }

    // Ham cho button go
    public void goButton(View view){
        String url = editText.getText().toString();
        webView.loadUrl(url);
    }
}