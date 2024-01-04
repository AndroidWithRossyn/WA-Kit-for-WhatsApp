package com.SachinApps.Whatscan.Pro.WhatsClone.qr_reader;

import android.hardware.Camera;

public class CamWrapper {
    public final Camera mCamera;
    public final int mCameraId;

    private CamWrapper(Camera camera, int i) {
        if (camera != null) {
            this.mCamera = camera;
            this.mCameraId = i;
            return;
        }
        throw new NullPointerException("Camera cannot be null");
    }

    public static CamWrapper getWrapper(Camera camera, int i) {
        if (camera == null) {
            return null;
        }
        return new CamWrapper(camera, i);
    }
}
