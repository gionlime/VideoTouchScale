package com.yunbo.media.video.gesture.touch.handler;

/**
 * create by jeek
 * 2022/5/7
 * des: 手势旋转处理
 **/
public interface IVideoRotateHandler extends IVideoTouchHandler {
    /**
     * 是否处于旋转中
     *
     * @return
     */
    boolean isRotated();

    /**
     * 获取旋转角度度数
     *
     * @return
     */
    float getCurrentRotateDegree();

    /**
     * 最终旋转回弹后的角度
     *
     * @return
     */
    float getTargetRotateDegree();

    /**
     * 旋转回弹动画结束后，更新补偿的角度
     *
     * @param fixRotateDegree 旋转结束动画补偿角度
     */
    void fixRotateEndAnim(float fixRotateDegree);

    void cancelRotate();
}
