package com.ptithcm.customerbroastcastreceiver_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String MY_ACTION = "com.ptithcm.ACTION";
    private static final String MY_TEXT = "com.ptithcm.TEXT";

    private Button btn_send_broadcast;
    private TextView tv_received;

    private BroadcastReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_send_broadcast = findViewById(R.id.btn_send_broadcast);
        tv_received = findViewById(R.id.tv_received);

        btn_send_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadcast();
            }
        });
    }

//    private BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (MY_ACTION.equals(intent.getAction())) {
//                String data = intent.getStringExtra(MY_TEXT);
//                tv_received.setText(data);
//                Toast.makeText(context, "Received: " + data, Toast.LENGTH_SHORT).show();
//            }
//        }
//    };


    private void clickSendBroadcast() {
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_TEXT, "This is test BroadcastReceiver Customer");
        sendBroadcast(intent);
    }

}