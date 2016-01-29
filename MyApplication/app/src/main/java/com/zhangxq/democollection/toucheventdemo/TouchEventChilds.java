package com.zhangxq.democollection.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchEventChilds extends LinearLayout {

    private ChildTouchListener listener;

    public TouchEventChilds(Context context) {
        super(context);
    }

    public TouchEventChilds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        String message = "Childs | dispatch --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onChildTouch(message);
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String message = "Childs | intercept --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onChildTouch(message);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        String message = "Childs | touchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction());
        if (listener != null) {
            listener.onChildTouch(message);
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (listener != null) {
                listener.onChildTouchUp();
            }
        }
        return false;
    }

    /**
     * 设置子view触摸监听
     *
     * @param listener
     */
    public void setListener(ChildTouchListener listener) {
        this.listener = listener;
    }

    /**
     * 子view触摸监听
     */
    public interface ChildTouchListener {
        void onChildTouch(String textView);
        void onChildTouchUp();
    }
}