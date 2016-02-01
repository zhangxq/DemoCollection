package com.zhangxq.democollection.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchEventChilds extends View {

    public ReturnType dispatchReturnType;
    public ReturnType onTouchReturnType;

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

        if (dispatchReturnType == ReturnType.YES) {
            return true;
        } else if (dispatchReturnType == ReturnType.NO) {
            return false;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        String message = "Childs | intercept --> " + TouchEventUtil.getTouchAction(ev.getAction());
//        if (listener != null) {
//            listener.onChildTouch(message);
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

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
    public void setListener(ChildTouchListener listener) {
        this.listener = listener;
        dispatchReturnType = ReturnType.DEFAULT;
        onTouchReturnType = ReturnType.DEFAULT;
    }

    /**
     * 子view触摸监听
     */
    public interface ChildTouchListener {
        void onChildTouch(String textView);
        void onChildTouchUp();
    }
}