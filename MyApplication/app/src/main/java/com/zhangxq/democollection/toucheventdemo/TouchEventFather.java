package com.zhangxq.democollection.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {

    public ReturnType dispatchReturnType;
    public ReturnType onTouchReturnType;

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

        if (dispatchReturnType == ReturnType.YES) {
            return true;
        } else if (dispatchReturnType == ReturnType.NO) {
            return false;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        String message = "Father | intercept --> " + TouchEventUtil.getTouchAction(ev.getAction());
//        if (listener != null) {
//            listener.onFatherTouch(message);
//        }
//        return true;
//    }

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

        if (onTouchReturnType == ReturnType.YES) {
            return true;
        } else if (onTouchReturnType == ReturnType.NO) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
    }

    /**
     * 设置子view触摸监听
     *
     * @param listener
     */
    public void setListener(FatherTouchListener listener) {
        this.listener = listener;
        dispatchReturnType = ReturnType.DEFAULT;
        onTouchReturnType = ReturnType.DEFAULT;
    }

    /**
     * 子view触摸监听
     */
    public interface FatherTouchListener {
        void onFatherTouch(String textView);
        void onFatherTouchUp();
    }

}