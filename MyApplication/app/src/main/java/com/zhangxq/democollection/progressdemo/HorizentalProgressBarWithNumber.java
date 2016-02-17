package com.zhangxq.democollection.progressdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.zhangxq.democollection.R;
import com.zhangxq.democollection.utils.Util;

/**
 * 1.自定义属性
 * 2.构造方法中获取
 * 3.onMeasure
 * 4.onDraw
 */
public class HorizentalProgressBarWithNumber extends ProgressBar {
    private static final int DEFAUTL_UNREACHED_COLOR = 0xff000000;
    private static final int DEFAUTL_REACHED_COLOR = 0xff00ff00;
    private static final int DEFAUTL_UNREACHED_HEIGHT = 10;
    private static final int DEFAUTL_REACHED_HEIGHT = 20;
    private static final int DEFAUTL_TEXT_SIZE = 18;
    private static final int DEFAUTL_TEXT_COLOR = 0xff0000ff;
    private static final int DEFAUTL_TEXT_OFFSET = 5;
    private static final int DEFAUTL_TEXT_VISIBLITY = 0;

    private boolean ifDrawText = true;
    private int mRealWidth = 10; //progressbar实际宽度
    private boolean noNeedBg = false;

    private Paint mpaint = new Paint();
    private int progressbar_unreached_height;
    private int progressbar_reached_height;

    public HorizentalProgressBarWithNumber(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizentalProgressBarWithNumber(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(true);
        obtainStyledAttribute(attrs);
    }

    //获取自定义属性
    private void obtainStyledAttribute(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizentalProgressBarWithNumber);
        int progressbar_unreached_color = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_unreached_color, DEFAUTL_UNREACHED_COLOR);
        int progressbar_reached_color = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_reached_color, DEFAUTL_REACHED_COLOR);
        progressbar_unreached_height = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_unreached_height, Util.DensityUtil.dip2px(getContext(), DEFAUTL_UNREACHED_HEIGHT));
        progressbar_reached_height = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_reached_height, Util.DensityUtil.dip2px(getContext(), DEFAUTL_REACHED_HEIGHT));
        int progressbar_text_size = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_text_size, Util.DensityUtil.dip2px(getContext(), DEFAUTL_TEXT_SIZE));
        int progressbar_text_color = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_text_color, DEFAUTL_TEXT_COLOR);
        int progressbar_text_offset = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_text_offset, Util.DensityUtil.dip2px(getContext(), DEFAUTL_TEXT_OFFSET));
        int progressbar_text_visiblity = typedArray.getColor(R.styleable.HorizentalProgressBarWithNumber_progressbar_text_visiblity, DEFAUTL_TEXT_VISIBLITY);

        if (progressbar_text_visiblity != DEFAUTL_TEXT_VISIBLITY) {
            ifDrawText = false;
        }
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != MeasureSpec.EXACTLY) {
            float textHeight = mpaint.descent() + mpaint.ascent();
            int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math.max(textHeight, Math.max(progressbar_unreached_height, progressbar_reached_height)));
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        int max = getMax();
        float radio = getProgress() * 1.0f / max;
        float reachedWidth = radio * mRealWidth;
        String text = getProgress() + "%";
        //字体宽高
        float textWidth = mpaint.measureText(text);
        float textHeight = (mpaint.descent() + mpaint.ascent()) / 2;
        //最后面不需要绘制
        if (reachedWidth + textWidth > mRealWidth) {
            reachedWidth = mRealWidth - textWidth;
            noNeedBg = true;
        }
        //绘制进度
        float endX = reachedWidth - DEFAUTL_TEXT_OFFSET / 2;
        if (endX > 0) {
            mpaint.setColor(DEFAUTL_REACHED_COLOR);
            mpaint.setStrokeWidth(Util.DensityUtil.dip2px(getContext(), DEFAUTL_REACHED_HEIGHT));
            canvas.drawLine(0, 0, endX, 0, mpaint);
        }
        //绘制文本
        if (ifDrawText) {
            mpaint.setColor(DEFAUTL_TEXT_COLOR);
            canvas.drawText(text, reachedWidth, -textHeight, mpaint);
        }
        //绘制未达到的进度条
        if (!noNeedBg) {
            float start = reachedWidth + DEFAUTL_TEXT_OFFSET / 2 + textWidth;
            mpaint.setColor(DEFAUTL_UNREACHED_COLOR);
            mpaint.setStrokeWidth(Util.DensityUtil.dip2px(getContext(), DEFAUTL_UNREACHED_HEIGHT));
            canvas.drawLine(start, 0, mRealWidth, 0, mpaint);
        }
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRealWidth = w - getPaddingLeft() - getPaddingRight();
    }
}
