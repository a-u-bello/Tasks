package com.example.ahmed.simpdo.presentation.list;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.ahmed.simpdo.presentation.notifications.ImportantService;

/**
 * Created by ahmed on 9/8/17.
 */

public abstract class BackgroundFragment extends Fragment {
    private static final String TAG = "BackGroundFragment";
    private BroadcastReceiver backReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Receiving this intent means the fragment is visible
            // hence it's cancelled
            Log.d(TAG, "canceling notification");
            setResultCode(Activity.RESULT_CANCELED);
        }
    };

    @Override
    public void onStart(){
        super.onStart();

        IntentFilter filter = new IntentFilter(ImportantService.IMPORTANT_SERVICE);
        getActivity().registerReceiver(backReceiver, filter,
                ImportantService.PRIVATE, null);
    }

    @Override
    public void onStop(){
        super.onStop();

        getActivity().unregisterReceiver(backReceiver);
    }
}
