package com.yunbo.media.video.gesture.touch.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunbo.media.video.R;

/**
 * create by jeek
 * 2022/5/7
 * des:
 **/
public abstract class TouchScaleResetView implements View.OnClickListener {
    private Context mContext;
    private View mScaleResetContent;
    private View mScaleResetView;

    public TouchScaleResetView(Context context, ViewGroup container) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.touch_scale_rest_view, container);
        mScaleResetContent = view.findViewById(R.id.view_scale_reset);
        mScaleResetView = view.findViewById(R.id.tv_scale_reset);
        mScaleResetView.setOnClickListener(this);
    }

    public int getVisibility() {
        return mScaleResetContent.getVisibility();
    }

    public void setVisibility(int visibility) {
        mScaleResetContent.setVisibility(visibility);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_scale_reset:
                clickResetScale();
                break;
        }
    }

    public abstract void clickResetScale();
}
