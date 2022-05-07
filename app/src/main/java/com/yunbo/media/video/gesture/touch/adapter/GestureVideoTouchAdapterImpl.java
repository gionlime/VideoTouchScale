package com.yunbo.media.video.gesture.touch.adapter;

import android.view.TextureView;

import com.yunbo.media.video.controller.VideoPlayController;
import com.yunbo.media.video.gesture.touch.anim.IVideoTouchEndAnim;
import com.yunbo.media.video.gesture.touch.handler.IVideoRotateHandler;

/**
 * create by jeek
 * 2022/5/7
 * des: 播放器手势触摸适配，兼容HkBaseVideoView升级到新播放器BaseVideoPlayer
 **/
public class GestureVideoTouchAdapterImpl implements IVideoTouchAdapter {
    VideoPlayController mPlayController;
    private IVideoRotateHandler mRotateHandler;
    private IVideoTouchEndAnim mTouchEndAnim;

    public GestureVideoTouchAdapterImpl(VideoPlayController playController) {
        mPlayController = playController;
    }

    public void setTouchEndAnim(IVideoTouchEndAnim touchEndAnim) {
        mTouchEndAnim = touchEndAnim;
    }

    @Override
    public IVideoTouchEndAnim getVideoTouchEndAnim() {
        return mTouchEndAnim;
    }

    @Override
    public IVideoRotateHandler getVideoRotateHandler() {
        return mRotateHandler;
    }

    public void setVideoRotateHandler(IVideoRotateHandler rotateHandler) {
        mRotateHandler = rotateHandler;
    }

    @Override
    public TextureView getTextureView() {
        if (mPlayController instanceof TextureView) {
            return (TextureView) mPlayController;
        }
        return null;
    }

    @Override
    public boolean isPlaying() {
        return mPlayController.isPlaying();
    }

    @Override
    public boolean isFullScreen() {
        return false;
    }
}
