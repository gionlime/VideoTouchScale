package com.yunbo.media.video.gesture.touch.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * create by jeek
 * 2022/5/7
 * des:
 **/
public interface IVideoGestureListener extends GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    boolean onTouchEvent(MotionEvent event);
}