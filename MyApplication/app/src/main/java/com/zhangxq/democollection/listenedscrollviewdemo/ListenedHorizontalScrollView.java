package com.zhangxq.democollection.listenedscrollviewdemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhangxq on 16/2/15.
 */
public class ListenedHorizontalScrollView extends HorizontalScrollView {
    private float positionX;
    private OnScrollListener listener;
    private Timer timer;
    private Handler handler = new MyHandler();

    public ListenedHorizontalScrollView(Context context) {
        super(context);
    }

    public ListenedHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenedHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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
            float x = ListenedHorizontalScrollView.this.getScrollX();
            if (x == positionX) {
                Message message = new Message();
                message.what = 0;
                handler.sendMessage(message);
            } else if (x != positionX) {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            positionX = x;
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (listener != null) {
                        listener.onScrollEnd(ListenedHorizontalScrollView.this);
                    }
                    break;
                case 1:
                    if (listener != null) {
                        listener.onScroll(ListenedHorizontalScrollView.this);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
