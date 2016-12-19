package com.example.chuta.glassfacedetection;

import android.graphics.Bitmap;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
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
        for (Mat m : mats)
        {
            newMats.put(0, ocvToJcv(m)); //TODO what does the 0 do?
        }
        return newMats;
    }


    //opencv Mat -> javacv Mat
    public static opencv_core.Mat ocvToJcv(Mat source)
    {
        return bmpToJcv(ocvToBmp(source));
    }

    //javacv Mat -> opencv Mat
    public static Mat jcvToOcv(opencv_core.Mat source)
    {
        return bmpToOcv(jcvToBmp(source));
    }

    private static Bitmap ocvToBmp(Mat source)
    {
        Bitmap bmp = Bitmap.createBitmap(source.rows(), source.cols(),Bitmap.Config.ARGB_8888);
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

    private static Bitmap jcvToBmp(opencv_core.Mat source)
    {
        OpenCVFrameConverter.ToMat frameConverter = new OpenCVFrameConverter.ToMat();
        AndroidFrameConverter bmpConverter = new AndroidFrameConverter();
        return bmpConverter.convert(frameConverter.convert(source));
    }

}
