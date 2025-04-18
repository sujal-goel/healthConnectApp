
package com.example.healthconnect;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HealthDataUploader {
    public static void upload(Context context) {
        // Simulated JSON payload
        final String jsonData = "{ \"user\": \"user1\", \"steps\": 1234, \"heartRate\": 78 }";
        final String serverUrl = "https://your-backend.com/api/health";

        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    os.write(jsonData.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    Log.i("Uploader", "Response code: " + responseCode);
                } catch (Exception e) {
                    Log.e("Uploader", "Error posting data", e);
                }
                return null;
            }
        }.execute();
    }
}
