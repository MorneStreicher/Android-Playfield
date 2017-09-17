package com.mornestreicher.helloworld1.services.message;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.mornestreicher.helloworld1.services.util.IGetService;

public class MessageService extends Service {
    private final IBinder myBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getNewMessage() {
        return "New message from service";
    }

    public class LocalBinder extends Binder implements IGetService<MessageService> {
        public MessageService getService() {
            return MessageService.this;
        }
    }
}
