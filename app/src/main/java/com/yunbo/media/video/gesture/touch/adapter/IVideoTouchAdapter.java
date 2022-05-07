package com.yunbo.media.video.gesture.touch.adapter;

import android.view.TextureView;

import androidx.annotation.Nullable;

import com.yunbo.media.video.gesture.touch.anim.IVideoTouchEndAnim;
import com.yunbo.media.video.gesture.touch.handler.IVideoRotateHandler;

/**
 * create by jeek
 * 2022/5/7
 * des: 播放器手势触摸适配，手势与播放器之间的适配层
 **/
public interface IVideoTouchAdapter {

    @Nullable
    IVideoTouchEndAnim getVideoTouchEndAnim();

    IVideoRotateHandler getVideoRotateHandler();

    TextureView getTextureView();

    boolean isPlaying();

    boolean isFullScreen();
}
