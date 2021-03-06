package com.yunbo.media.video;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentActivity;

import com.yunbo.media.video.constants.URL;
import com.yunbo.media.video.gesture.GestureLayer;
import com.yunbo.media.video.gesture.touch.adapter.GestureVideoTouchAdapterImpl;
import com.yunbo.media.video.surface.TextureViewPlayer;
import com.yunbo.media.video.util.StatusBarUtils;

/**
 * create by jeek
 * 2022/5/7
 * des: ScaleVideoActivity
 **/
public class ScaleVideoActivity extends FragmentActivity {
    public TextureViewPlayer mTextureViewPlayer;
    private ViewGroup mVideoContent;
    private ImageButton mPlayPauseView;
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.fullScreen(getWindow());
        setContentView(R.layout.activity_scale_video);
        mVideoContent = findViewById(R.id.video_content);
        mTextureViewPlayer = findViewById(R.id.texture_player);
        mPlayPauseView = findViewById(R.id.btn_play_pause);
        initPlayer();
        initGesture(mVideoContent);
        initData();
    }

    private void initData() {
        mPlayPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextureViewPlayer.isPlaying()) {
                    mTextureViewPlayer.pause();
                    changePlayBtnStyle(false);
                } else if (isPaused) {
                    mTextureViewPlayer.start();
                    isPaused = false;
                    changePlayBtnStyle(true);
                } else {
                    mTextureViewPlayer.startPlay(URL.getPlayUrl());
                    changePlayBtnStyle(true);
                }
            }
        });
//        mTextureViewPlayer.startPlay(getPlayUrl());
    }


    private void changePlayBtnStyle(boolean isPlaying) {
        if (isPlaying) {
            mPlayPauseView.setImageResource(R.drawable.small_new_pip_pause);
        } else {
            mPlayPauseView.setImageResource(R.drawable.small_new_pip_play);
        }
    }

    private void initGesture(ViewGroup videoContent) {
        GestureLayer gestureLayer = new GestureLayer(this,
                new GestureVideoTouchAdapterImpl(mTextureViewPlayer) {
                    @Override
                    public boolean isFullScreen() {
                        return true;
                    }
                });
        videoContent.addView(gestureLayer.getContainer());
    }

    private void initPlayer() {
        mTextureViewPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mTextureViewPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

            }
        });
        mTextureViewPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                String newUrl = URL.getPlayUrl(0);
                if (mTextureViewPlayer.getPlayUrl().equals(URL.getPlayUrl(0))) {
                    newUrl = URL.getPlayUrl(1);
                }
                mTextureViewPlayer.startPlay(newUrl);
            }
        });
    }
}