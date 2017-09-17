package com.mornestreicher.helloworld1.activities.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mornestreicher.helloworld1.R;
import com.mornestreicher.helloworld1.services.message.MessageService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MessageService messageService;
    private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.i(TAG, "onServiceConnected was called");
            MessageService.MyLocalBinder binder = (MessageService.MyLocalBinder) service;
            messageService = binder.getService();
        }

        public void onServiceDisconnected(ComponentName arg0) {
            Log.i(TAG, "onServiceDisconnected was called");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add widget event listeners
        addWidgetEventListeners();

        // Bind to our local services
        Log.i(TAG, "bindService is about to be called");
        Intent intent = new Intent(this, MessageService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "bindService was called");
    }

    private void addWidgetEventListeners() {
        //
        // Button: Change text
        //
        Button button = (Button) findViewById(R.id.button_get_message);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textViewMessage);
                tv.setText(messageService.getNewMessage());
            }
        });
    }
}
