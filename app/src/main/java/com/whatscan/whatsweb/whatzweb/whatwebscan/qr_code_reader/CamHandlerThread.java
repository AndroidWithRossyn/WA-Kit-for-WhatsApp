package com.whatscan.whatsweb.whatzweb.whatwebscan.qr_code_reader;

import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class CamHandlerThread extends HandlerThread {
    private static final String LOG_TAG = "CameraHandlerThread";
    
    public BarcodeScannerView mScannerView;

    public CamHandlerThread(BarcodeScannerView barcodeScannerView) {
        super(LOG_TAG);
        this.mScannerView = barcodeScannerView;
        start();
    }

    public void startCamera(final int i) {
        new Handler(getLooper()).post(new Runnable() {
            @Override
            public void run() {
                final Camera cameraInstance = CamUtils.getCameraInstance(i);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        mScannerView.setupCameraPreview(CamWrapper.getWrapper(cameraInstance, i));
                    }
                });
            }
        });
    }
}
