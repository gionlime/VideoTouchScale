package com.yunbo.media.video.gesture;

import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * create by jeek
 * 2022/5/7
 * des:
 **/
public interface IGestureLayer {
    FrameLayout getContainer();

    /**
     * 事件处理器
     */
    void initTouchHandler();

    /**
     * 分发touch事件
     *
     * @param event
     * @return
     */
    boolean onGestureTouchEvent(MotionEvent event);

    void onLayerRelease();
}
