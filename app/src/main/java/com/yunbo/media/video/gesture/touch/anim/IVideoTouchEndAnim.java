package com.yunbo.media.video.gesture.touch.anim;


/**
 * create by jeek
 * 2022/5/7
 * des:
 **/
public interface IVideoTouchEndAnim {

    void setEndAnimScale(float scale);

    void setEndAnimRotate(float currentRotate, float rotateEndFixDegrees);

    void startAnim();

    void endPrevAnim();
}