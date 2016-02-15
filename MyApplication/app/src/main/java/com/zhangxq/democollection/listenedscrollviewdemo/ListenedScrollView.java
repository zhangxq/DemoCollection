package com.zhangxq.democollection.listenedscrollviewdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 一款可以检测scrollView滚动状态的ScrollView，是否正在滚动，是否停止等
 * 实现原理：使用Timer获取scrollY的变动，判断当前是否处于滚动状态
 * <p/>
 * Created by zhangxq on 16/2/15.
 */
public class ListenedScrollView extends ScrollView {
    private float positionY;
    private OnScrollListener listener;
    private Timer timer;
    private Handler handler = new MyHandler();

    public ListenedScrollView(Context context) {
        super(context);
    }

    public ListenedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置滚动状态监听
     *
     * @param listener
     */
    public void setOnScrollListener(final OnScrollListener listener) {
        this.listener = listener;
        timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, 100);
    }

    /**
     * 释放资源，在activity的ondestroy里调用
     */
    public void releaseListener() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            float y = ListenedScrollView.this.getScrollY();
            if (y == positionY) {
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            } else if (y != positionY) {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            positionY = y;
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (listener != null) {
                        listener.onScrollEnd(ListenedScrollView.this);
                    }
                    break;
                case 1:
                    if (listener != null) {
                        listener.onScroll(ListenedScrollView.this);
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
