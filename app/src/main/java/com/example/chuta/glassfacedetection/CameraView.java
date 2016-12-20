package com.example.chuta.glassfacedetection;



import org.opencv.android.JavaCameraView;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;

public class CameraView extends JavaCameraView
{
    public CameraView(Context context, int cameraId) {
        super(context, cameraId);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean initializeCamera(int width, int height)
    {
        super.initializeCamera(width, height);

        Camera.Parameters params = mCamera.getParameters();

        // Post XE10 Hotfix
        params.setPreviewFpsRange(30000, 30000);
        mCamera.setParameters(params);

        Log.d("CameraView", "Hello?");
        return true;
    }

    public void freeCamera()
    {
        mCamera.release();
    }
}