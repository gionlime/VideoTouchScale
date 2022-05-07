package com.yunbo.media.video.constants;

import android.text.TextUtils;

/**
 * create by jeek
 * 2022/5/7
 * des:
 **/
public final class URL {
    private static String[] URLS = new String[]{
            "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f230000btcaac52m1gham4830p0&ratio=720p&line=0",
            "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200f230000btcaac52m1gham4830p0&ratio=720p&line=0",
    };

    public static String getPlayUrl() {
        return getPlayUrl(0);
    }

    public static String getPlayUrl(int index) {
        return URLS[index];
    }

    public static String getNextUrl(String playUrl) {
        if (!TextUtils.isEmpty(playUrl)) {
            for (int i = 0; i < URLS.length; i++) {
                if (playUrl.equals(URLS[i])) {
                    return getPlayUrl((i + 1) % URLS.length);
                }
            }
        }
        return getPlayUrl(0);
    }
}
