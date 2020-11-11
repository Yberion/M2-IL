package fr.brandon.mmm.tp3;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    private static final String TAG = "ActivityFirebaseMessagingService";

    @Override
    public void onNewToken(@NonNull String token)
    {
        Log.d(TAG, "Refreshed token: " + token);
    }
}
