package com.zhangxq.democollection.toucheventdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchEventActivity extends Activity implements TouchEventChilds.ChildTouchListener,
        TouchEventFather.FatherTouchListener, RadioGroup.OnCheckedChangeListener {
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
    @Bind(R.id.radioGroupChildDispatch)
    RadioGroup radioGroupChildDispatch;
    @Bind(R.id.radioGroupChildOnTouch)
    RadioGroup radioGroupChildOnTouch;
    @Bind(R.id.radioGroupFatherDispatch)
    RadioGroup radioGroupFatherDispatch;
    @Bind(R.id.radioGroupFatherOnTouch)
    RadioGroup radioGroupFatherOnTouch;

    String message = "";

    private boolean fatherEnable = true;
    private boolean childEnable = true;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_event);
        ButterKnife.bind(this);

        viewFather.setListener(this);
        viewChild.setListener(this);

        radioGroupChildDispatch.setOnCheckedChangeListener(this);
        radioGroupChildOnTouch.setOnCheckedChangeListener(this);
        radioGroupFatherDispatch.setOnCheckedChangeListener(this);
        radioGroupFatherOnTouch.setOnCheckedChangeListener(this);

        radioGroupChildDispatch.check(R.id.radioButtonChildDispatchDefault);
        radioGroupChildOnTouch.check(R.id.radioButtonChildOntouchDefault);
        radioGroupFatherDispatch.check(R.id.radioButtonFatherDispatchDefault);
        radioGroupFatherOnTouch.check(R.id.radioButtonFatherOntouchDefault);
    }

    @OnClick(R.id.buttonRed)
    void onButtonRedClick() {
        if (childEnable) {
            viewChild.setClickable(false);
            buttonRed.setText("红色不可点");
        } else {
            viewChild.setClickable(true);
            buttonRed.setText("红色可点");
        }
        childEnable = !childEnable;
    }

    @OnClick(R.id.buttonBlue)
    void onButtonBlueClick() {
        if (fatherEnable) {
            viewFather.setClickable(false);
            buttonBlue.setText("蓝色不可点");
        } else {
            viewFather.setClickable(true);
            buttonBlue.setText("蓝色可点");
        }
        fatherEnable = !fatherEnable;
    }

    @OnClick(R.id.buttonClear)
    void onButtonClearClick() {
        message = "";
        textView.setText(message);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
//        message += "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()) + "\n";
//        textView.setText(message);
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
//        message += "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()) + "\n";
//        textView.setText(message);
        return super.onTouchEvent(event);
    }

    @Override
    public void onChildTouch(String text) {
        Log.d("child", text);
        message = message + text + "\n";
        textView.setText(message);
    }

    @Override
    public void onFatherTouch(String text) {
        Log.d("father", text);
        message = message + text + "\n";
        textView.setText(message);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.radioGroupChildDispatch:
                if (checkedId == group.getChildAt(1).getId()) {
                    viewChild.dispatchReturnType = ReturnType.YES;
                } else if (checkedId == group.getChildAt(2).getId()) {
                    viewChild.dispatchReturnType = ReturnType.NO;
                } else {
                    viewChild.dispatchReturnType = ReturnType.DEFAULT;
                }
                break;
            case R.id.radioGroupChildOnTouch:
                if (checkedId == group.getChildAt(1).getId()) {
                    viewChild.onTouchReturnType = ReturnType.YES;
                } else if (checkedId == group.getChildAt(2).getId()) {
                    viewChild.onTouchReturnType = ReturnType.NO;
                } else {
                    viewChild.onTouchReturnType = ReturnType.DEFAULT;
                }
                break;
            case R.id.radioGroupFatherDispatch:
                if (checkedId == group.getChildAt(1).getId()) {
                    viewFather.dispatchReturnType = ReturnType.YES;
                } else if (checkedId == group.getChildAt(2).getId()) {
                    viewFather.dispatchReturnType = ReturnType.NO;
                } else {
                    viewFather.dispatchReturnType = ReturnType.DEFAULT;
                }
                break;
            case R.id.radioGroupFatherOnTouch:
                if (checkedId == group.getChildAt(1).getId()) {
                    viewFather.onTouchReturnType = ReturnType.YES;
                } else if (checkedId == group.getChildAt(2).getId()) {
                    viewFather.onTouchReturnType = ReturnType.NO;
                } else {
                    viewFather.onTouchReturnType = ReturnType.DEFAULT;
                }
                break;
            default:
                break;
        }
    }
}