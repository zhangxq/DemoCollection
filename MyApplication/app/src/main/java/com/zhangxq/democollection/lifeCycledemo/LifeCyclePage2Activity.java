package com.zhangxq.democollection.lifeCycledemo;

import android.app.Activity;
import android.os.Bundle;

import com.zhangxq.democollection.R;

public class LifeCyclePage2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_page2);
    }
}
