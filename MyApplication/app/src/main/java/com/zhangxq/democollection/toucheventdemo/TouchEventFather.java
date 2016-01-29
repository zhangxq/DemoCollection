package com.zhangxq.democollection.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {

    private FatherTouchListener listener;

    public TouchEventFather(Context context) {
        super(context);
    }

    public TouchEventFather(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        String message = "Father | dispatch --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onFatherTouch(message);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String message = "Father | intercept --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onFatherTouch(message);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        String message = "Father | touchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onFatherTouch(message);
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (listener != null) {
                listener.onFatherTouchUp();
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 设置子view触摸监听
     *
     * @param listener
     */
    public void setListener(FatherTouchListener listener) {
        this.listener = listener;
    }

    /**
     * 子view触摸监听
     */
    public interface FatherTouchListener {
        void onFatherTouch(String textView);
        void onFatherTouchUp();
    }

}