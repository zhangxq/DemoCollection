package com.zhangxq.democollection.listenedscrollviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangxq on 16/2/15.
 */
public class ScrollActivity extends Activity implements OnScrollListener {
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.verticalScrollView)
    ListenedScrollView verticalScrollView;
    @Bind(R.id.horizontalScrollView)
    ListenedHorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.bind(this);

        verticalScrollView.setOnScrollListener(this);
        horizontalScrollView.setOnScrollListener(this);
    }

    @Override
    public void onScroll(View scrollView) {
        if (scrollView.getId() == verticalScrollView.getId()) {
            textView.setText("滚动状态");
        } else {
            textView1.setText("滚动状态");
        }
    }

    @Override
    public void onScrollEnd(View scrollView) {
        if (scrollView.getId() == verticalScrollView.getId()) {
            textView.setText("停止状态");
        } else {
            textView1.setText("停止状态");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        verticalScrollView.releaseListener();
        horizontalScrollView.releaseListener();
    }
}
