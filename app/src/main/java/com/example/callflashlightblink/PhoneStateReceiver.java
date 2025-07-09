package com.example.callflashlightblink;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;

public class PhoneStateReceiver extends BroadcastReceiver {

    private static boolean isBlinking = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String stateStr = intent.getStringExtra(android.telephony.TelephonyManager.EXTRA_STATE);

        if (android.telephony.TelephonyManager.EXTRA_STATE_RINGING.equals(stateStr)) {
            if (!isBlinking) {
                isBlinking = true;
                blinkFlashlight(context);
            }
        } else if (android.telephony.TelephonyManager.EXTRA_STATE_IDLE.equals(stateStr)) {
            isBlinking = false;
        }
    }

    private void blinkFlashlight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            Handler handler = new Handler();

            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                for (int i = 0; i < 10; i++) {
                    int delay = i * 600;
                    handler.postDelayed(() -> {
                        try {
                            cameraManager.setTorchMode(cameraId, true);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }, delay);

                    handler.postDelayed(() -> {
                        try {
                            cameraManager.setTorchMode(cameraId, false);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }, delay + 300);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
