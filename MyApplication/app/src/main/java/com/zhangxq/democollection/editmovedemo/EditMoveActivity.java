package com.zhangxq.democollection.editmovedemo;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zhangxq.democollection.R;

import io.vov.vitamio.widget.VideoView;

/**
 * Created by zhangxiaoqi on 2016/8/11.
 */
public class EditMoveActivity extends Activity {
    private LinearLayout linearLayout;
    private VideoView mVideoView;
    Rect outRect = new Rect();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_move);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mVideoView = (VideoView) findViewById(R.id.videoView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        ScrollView.LayoutParams params = (ScrollView.LayoutParams) linearLayout.getLayoutParams();
        params.height = outRect.bottom - outRect.top;
        play();
    }

    void play() {
        String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        mVideoView.setVideoPath(path);
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
        mVideoView.start();
    }
}
