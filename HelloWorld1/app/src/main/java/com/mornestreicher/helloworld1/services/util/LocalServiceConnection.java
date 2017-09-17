package com.mornestreicher.helloworld1.services.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class LocalServiceConnection<BINDER extends IGetService<SERVICE>, SERVICE> implements ServiceConnection {
    private SERVICE serviceInstance;

    public void onServiceConnected(ComponentName className,
                                   IBinder service) {
        serviceInstance = ((BINDER) service).getService();
    }

    public void onServiceDisconnected(ComponentName arg0) {
        serviceInstance = null;
    }

    public void bind(Context context, Class<?> serviceClass) {
        Intent intent = new Intent(context, serviceClass);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public SERVICE getService() {
        return this.serviceInstance;
    }
}
