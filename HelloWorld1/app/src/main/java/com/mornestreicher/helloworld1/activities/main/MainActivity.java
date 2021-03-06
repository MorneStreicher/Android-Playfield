package com.mornestreicher.helloworld1.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mornestreicher.helloworld1.R;
import com.mornestreicher.helloworld1.activities.listpeople.ListPeopleActivity;
import com.mornestreicher.helloworld1.services.message.MessageService;
import com.mornestreicher.helloworld1.services.util.LocalServiceConnection;

public class MainActivity extends AppCompatActivity {

    private LocalServiceConnection<MessageService.LocalBinder, MessageService> serviceConnection = new LocalServiceConnection<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add widget event listeners
        addWidgetEventListeners();

        // Bind to our local services
        serviceConnection.bind(this, MessageService.class);
    }

    private void addWidgetEventListeners() {
        //
        // Button: Change text
        //
        Button button = (Button) findViewById(R.id.button_get_message);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tv = (TextView) findViewById(R.id.textViewMessage);
                tv.setText(serviceConnection.getService().getNewMessage());
            }
        });

        //
        // Button: List people
        //
        button = (Button) findViewById(R.id.button_list_people);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListPeopleActivity.class);
                startActivity(intent);
            }
        });
    }
}
