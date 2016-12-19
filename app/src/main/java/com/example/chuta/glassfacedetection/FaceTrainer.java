package com.example.chuta.glassfacedetection;

import android.util.Log;

import org.opencv.contrib.FaceRecognizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chuta on 11/12/2016.
 */

public class FaceTrainer {

    private static final boolean safeRun = true;
    private static final String tag = "FaceTrainer";

    //crop must be in original
    public static Mat PrepareImage(Mat original, Rect crop, Size newSize) {
        if (safeRun) {
            if ((original.size().width < crop.x + crop.width) || (original.size().height < crop.y + crop.height)) {
                Log.e(tag, "PrepareImage: Rectangle out of bounds from given Mat");
                return null;
            }
            if (crop.width < newSize.width || crop.height < newSize.height) {
                Log.d(tag, "PrepareImage: Requires expanding given image. Consider feeding one that is more zoomed in");
                return null;
            }
        }
        Mat image = new Mat();
        Imgproc.resize(new Mat(original, crop), image, newSize);
        return image;
    }

    // mats must all be same size
    // matSize must be possible (ie: matSize width height are factors of mats.length
    public static Mat CombineMats(List<Mat> mats, int width) {
        if (safeRun) {
            Size actualSize = mats.get(0).size();
            for (Mat m : mats) {
                if (m.size() != actualSize) {
                    Log.e(tag, "CombineMats: Size of mats different");
                    return null;
                }
            }
            if (width != mats.size()) {
                Log.e(tag, "CombineMats: Bad matSize");
                return null;
            }
        }
        Mat image = new Mat();
        ArrayList<Mat> row = new ArrayList<Mat>();
        for (Mat m : mats)
        {
            row.add(m);
        }
        Core.hconcat(row, image);
        return image;
    }

}


