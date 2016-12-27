package com.example.chuta.glassfacedetection;

import android.graphics.Bitmap;
import android.util.Log;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacpp.opencv_imgproc;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.ArrayList;


/**
 * Created by chuta on 12/19/2016.
 */

    //small lib for converting javacv Mats to opencv Mats and vice versa
    //other opencv <-> javacv related utilities are also here

public class CVLibTools {

    //ArrayList<opencv Mat> -> MatVector (javacv)
    public static opencv_core.MatVector omatsToJmats(ArrayList<Mat> mats)
    {
        opencv_core.MatVector newMats = new opencv_core.MatVector(mats.size());
        int i=0;
        for (Mat m : mats)
        {
            opencv_core.Mat m2 = ocvToJcvg(m);
            newMats.put(i, m2);
            i++;
            Log.d("CVLibTools", "Mat added");
        }

        return newMats;
    }


    //opencv Mat -> javacv Mat
    public static opencv_core.Mat ocvToJcv(Mat source)
    {
        return bmpToJcv(ocvToBmp(source));
    }
    public static opencv_core.Mat ocvToJcvg(Mat source) { return bmpToJcvg(ocvToBmp(source)); }

    //javacv Mat -> opencv Mat
    public static Mat jcvToOcv(opencv_core.Mat source)
    {
        return bmpToOcv(jcvToBmp(source));
    }

    private static Bitmap ocvToBmp(Mat source)
    {
        Bitmap bmp = Bitmap.createBitmap(source.rows(), source.cols(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(source, bmp);
        return bmp;
    }

    private static Mat bmpToOcv(Bitmap source)
    {
        Mat mat = new Mat(source.getWidth(), source.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(source, mat);
        return mat;
    }

    private static opencv_core.Mat bmpToJcv(Bitmap source)
    {
        OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();
        AndroidFrameConverter bmpConverter = new AndroidFrameConverter();
        return frameConverter.convert(bmpConverter.convert(source));
    }

    private static opencv_core.Mat bmpToJcvg(Bitmap source)
    {
        OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();
        AndroidFrameConverter bmpConverter = new AndroidFrameConverter();
        opencv_core.Mat bgr = frameConverter.convert(bmpConverter.convert(source));
        opencv_core.Mat gray = new opencv_core.Mat();
        opencv_imgproc.cvtColor(bgr, gray, opencv_imgproc.COLOR_BGRA2GRAY);
        return gray;
    }

    private static Bitmap jcvToBmp(opencv_core.Mat source)
    {
        OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();
        AndroidFrameConverter bmpConverter = new AndroidFrameConverter();
        return bmpConverter.convert(frameConverter.convert(source));
    }

}
