package com.mornestreicher.helloworld1.activities.listpeople;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mornestreicher.helloworld1.R;
import com.mornestreicher.helloworld1.services.message.MessageService;
import com.mornestreicher.helloworld1.services.util.LocalServiceConnection;

public class ListPeopleActivity extends AppCompatActivity {

    private LocalServiceConnection<MessageService.LocalBinder, MessageService> serviceConnection = new LocalServiceConnection<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpeople);

        // Add widget event listeners
        addWidgetEventListeners();

        // Bind to our local services
        serviceConnection.bind(this, MessageService.class);
    }

    private void addWidgetEventListeners() {
        //
        // Button: Button 2
        //
        final Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button.setText(serviceConnection.getService().getNewMessage());
            }
        });
    }
}
