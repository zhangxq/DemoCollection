package com.zhangxq.democollection.listenedscrollviewdemo;

import android.view.View;

/**
 * Created by zhangxq on 16/2/15.
 */
public interface OnScrollListener {
    /**
     * 滚动中
     *
     * @param scrollView
     */
    void onScroll(View scrollView);

    /**
     * 滚动停止
     *
     * @param scrollView
     */
    void onScrollEnd(View scrollView);
}
