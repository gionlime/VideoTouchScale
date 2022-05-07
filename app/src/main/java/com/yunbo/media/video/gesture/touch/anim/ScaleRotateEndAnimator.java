package com.yunbo.media.video.gesture.touch.anim;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;

import androidx.annotation.CallSuper;


/**
 * create by jeek
 * 2022/5/7
 * des: 缩放动画
 **/
public abstract class ScaleRotateEndAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener,
        Animator.AnimatorListener {
    /**
     * 图片缩放动画时间
     */
    public static final int SCALE_ANIMATOR_DURATION = 1000;
    private static final String TAG = "VideoScaleEndAnimator";
    private Matrix mStartMatrix = new Matrix();
    private Matrix mEndMatrix = new Matrix();
    private Matrix mMatrix = new Matrix();
    private float[] mStartMatrixValue;
    private float[] mInterpolateMatrixValue;
    private float[] mEndMatrixValue;
    private float mRotateDegrees;


    public void setScaleEndAnimParams(Matrix startMatrix, Matrix endMatrix, float rotateFixDegree) {
        mStartMatrix = startMatrix;
        mEndMatrix = endMatrix;
        mRotateDegrees = rotateFixDegree;
        mMatrix.reset();
        if (mStartMatrix == null || mEndMatrix == null) {
            return;
        }
        mStartMatrixValue = new float[9];
        mStartMatrix.getValues(mStartMatrixValue);
        mEndMatrixValue = new float[9];
        mEndMatrix.getValues(mEndMatrixValue);
        mInterpolateMatrixValue = new float[9];

        setAnimConfig();
    }

    protected void setAnimConfig() {
        setFloatValues(0, 1f);
        setDuration(SCALE_ANIMATOR_DURATION);
        addUpdateListener(this);
        addListener(this);
    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        // 获取动画进度
        float value = (Float) animation.getAnimatedValue();
        onValueUpdate(value);
    }


    public void onValueUpdate(float value) {
        if (mStartMatrix == null
                || mEndMatrix == null) {
            return;
        }
        for (int i = 0; i < 9; i++) {
            mInterpolateMatrixValue[i] = mStartMatrixValue[i] + (mEndMatrixValue[i] - mStartMatrixValue[i]) * value;
        }
        mMatrix.setValues(mInterpolateMatrixValue);
        updateMatrixToView(mMatrix);
    }


    protected abstract void updateMatrixToView(Matrix transMatrix);

    protected abstract void onFixEndAnim(ValueAnimator animator, float fixEndDegrees);

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @CallSuper
    @Override
    public void onAnimationEnd(Animator animation) {
        onFixEndAnim(this, mRotateDegrees);
    }

    @CallSuper
    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

}