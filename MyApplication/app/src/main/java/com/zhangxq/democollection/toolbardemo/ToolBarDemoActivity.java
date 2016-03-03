package com.zhangxq.democollection.toolbardemo;

import android.os.Bundle;
import android.widget.Toast;

import com.zhangxq.democollection.R;

/**
 * Created by zhangxq on 16/2/16.
 */
public class ToolBarDemoActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);
        setTitle("自定义标题栏");
//        setButtonBackImage(R.drawable.actionbar_menu);
//        setButtonOptionImage(R.drawable.actionbar_menu);
    }

    @Override
    public void onToolBarBackPress() {
//        super.onToolBarBackPress();
        Toast.makeText(this, "back pressed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onToolBarOptionPress() {
//        super.onToolBarOptionPress();
        Toast.makeText(this, "option pressed", Toast.LENGTH_LONG).show();
    }
}
