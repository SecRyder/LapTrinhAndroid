package com.ptithcm.customerbroastcastreceiver_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String MY_ACTION = "com.ptithcm.ACTION";
    private static final String MY_TEXT = "com.ptithcm.TEXT";
    TextView tv_received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv_received = findViewById(R.id.tv_received);
    }

    private BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(MY_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(MY_TEXT);
                tv_received.setText(text);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        registerReceiver(mBroadCastReceiver,intentFilter,Context.RECEIVER_EXPORTED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
    }
}