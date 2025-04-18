
package com.example.healthconnect;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class HealthDataScheduler {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Context context;
    private final int intervalMs = 15 * 60 * 1000; // 15 minutes

    public HealthDataScheduler(Context context) {
        this.context = context;
    }

    public void startPeriodicSync() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                HealthDataUploader.upload(context);
                handler.postDelayed(this, intervalMs);
            }
        }, 1000); // First sync after 1 second
    }
}
