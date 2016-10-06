package com.momentoustech.catalog.data.Helper;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;

import com.momentoustech.catalog.domain.model.Entity;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Kleyvert on 10/4/16.
 *
 * Service with CountDownTimer for to consume
 * the eventBus and to make a new consult to the API
 */
public class CountDownService extends Service {

    public static CountDownTimer timer;
    public static boolean timeout;

    @Override
    public void onCreate() {
        super.onCreate();
        timeout = false;

        // Instance from counter
        timer = new CountDownTimer(2 * 60 * 1000, 1000) { // each 2 minutes
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onFinish() {
                timeout = true;
                EventBus.getDefault().post(new Entity(Constant.PRODUCT_DEFAULT));

            }
        };
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (timer != null) {
            timer.start();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public boolean stopService(Intent name) {

        if (timer != null) {
            timer.cancel();
        }

        return super.stopService(name);
    }

}

