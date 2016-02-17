package com.zhangxq.democollection.progressdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.zhangxq.democollection.R;
import com.zhangxq.democollection.utils.Util;

/**
 * 1.自定义属性
 * 2.构造方法中获取
 * 3.onMeasure
 * 4.onDraw
 */
public class RoundProgressBarWithNumber extends HorizentalProgressBarWithNumber {
    private int DEFAULT_RADIUS = 20;

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RoundProgressBarWithNumber);
        float radius = typedArray.getDimension(R.styleable.RoundProgressBarWithNumber_radius, Util.DensityUtil.dip2px(getContext(), DEFAULT_RADIUS));
    }

}
