package com.zhangxq.democollection.androidwheeldemo;

import android.app.Activity;
import android.os.Bundle;

import com.zhangxq.democollection.R;
import com.zhangxq.democollection.androidwheeldemo.widget.WheelView;
import com.zhangxq.democollection.androidwheeldemo.widget.adapters.ArrayWheelAdapter;

public class AndroidWheelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_wheel);
        String[] strss={"001","002","003", "004", "005", "006", "007"};
        WheelView wheelview_main= (WheelView) findViewById(R.id.wheelview_main);
        wheelview_main.setViewAdapter(new ArrayWheelAdapter<String>(getApplicationContext(),strss));
        wheelview_main.setCurrentItem(1);
    }
}
