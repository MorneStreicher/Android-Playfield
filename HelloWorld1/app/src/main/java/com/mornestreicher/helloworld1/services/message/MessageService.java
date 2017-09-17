package com.mornestreicher.helloworld1.services.message;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Morne on 2017/09/17.
 */

public class MessageService extends Service {
    private final IBinder myBinder = new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getNewMessage() {
        return "New message from service";
    }

    public class MyLocalBinder extends Binder {
        public MessageService getService() {
            return MessageService.this;
        }
    }
}
