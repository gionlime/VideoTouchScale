package com.yunbo.media.video.gesture.touch.listener;

import android.view.ScaleGestureDetector;

import com.yunbo.media.video.gesture.IGestureLayer;
import com.yunbo.media.video.gesture.touch.handler.VideoTouchScaleHandler;

/**
 * create by jeek
 * 2022/5/7
 * des: 手势缩放 播放画面
 **/
public class VideoScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
    private static final String TAG = "VideoScaleGestureListener";
    public VideoTouchScaleHandler mScaleHandler;
    private IGestureLayer mGestureLayer;

    public VideoScaleGestureListener(IGestureLayer gestureLayer) {
        mGestureLayer = gestureLayer;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (mScaleHandler != null) {
            return mScaleHandler.onScale(detector);
        }
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        if (mScaleHandler != null) {
            boolean isConsume = mScaleHandler.onScaleBegin(detector);
            if (isConsume) {
                return true;
            }
        }
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        if (mScaleHandler != null) {
            mScaleHandler.onScaleEnd(detector);
        }

    }
}
