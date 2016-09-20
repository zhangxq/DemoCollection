package com.zhangxq.democollection.marqueedemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangxq.democollection.R;

/**
 * Created by zhangxiaoqi on 2016/8/24.
 */
public class MarqueeActivity extends Activity {
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        textView = (TextView) findViewById(R.id.textView);

        String testStr = "1234567890123456789012345678901234567890123456789012345678901234567890";
        Paint paint = textView.getPaint();
        int textViewWidth = (int) paint.measureText(testStr);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
        params.width = textViewWidth;
        SpannableString spannableString = new SpannableString(testStr);
        textView.setText(spannableString);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int screenWidth = metric.widthPixels;     // 屏幕宽度（像素）

        Animator animatorX = ObjectAnimator.ofFloat(textView, "translationX", -textViewWidth, screenWidth);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorX);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
