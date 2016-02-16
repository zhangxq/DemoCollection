package com.zhangxq.democollection.toolbardemo;

import android.os.Bundle;
import android.view.Menu;

import com.zhangxq.democollection.R;

/**
 * Created by zhangxq on 16/2/16.
 */
public class ToolBarDemoActivity extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_demo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
