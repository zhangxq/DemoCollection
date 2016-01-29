package com.zhangxq.democollection.toucheventdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchEventActivity extends Activity implements TouchEventChilds.ChildTouchListener, TouchEventFather.FatherTouchListener {
    @Bind(R.id.viewFather)
    TouchEventFather viewFather;
    @Bind(R.id.viewChild)
    TouchEventChilds viewChild;
    @Bind(R.id.buttonRed)
    Button buttonRed;
    @Bind(R.id.buttonBlue)
    Button buttonBlue;
    @Bind(R.id.textView)
    TextView textView;

    String message = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_event);
        ButterKnife.bind(this);

        viewFather.setListener(this);
        viewChild.setListener(this);
    }

    @OnClick(R.id.buttonRed)
    void onButtonRedClick() {
        if (viewChild.isClickable()) {
            viewChild.setClickable(false);
            buttonRed.setText("红色不可点");
        } else {
            viewChild.setClickable(true);
            buttonRed.setText("红色可点");
        }
    }

    @OnClick(R.id.buttonBlue)
    void onButtonBlueClick() {
        if (viewFather.isClickable()) {
            viewFather.setClickable(false);
            buttonBlue.setText("蓝色不可点");
        } else {
            viewFather.setClickable(true);
            buttonBlue.setText("蓝色可点");
        }
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
//        message += "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()) + "\n";
        textView.setText(message);
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
//        message += "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()) + "\n";
        textView.setText(message);
        return super.onTouchEvent(event);
    }

    @Override
    public void onChildTouch(String text) {
        message += text + "\n";
        textView.setText(message);
    }

    @Override
    public void onChildTouchUp() {
        message = "";
    }

    @Override
    public void onFatherTouch(String text) {
        message += text + "\n";
        textView.setText(message);
    }

    @Override
    public void onFatherTouchUp() {
        message = "";
    }
}