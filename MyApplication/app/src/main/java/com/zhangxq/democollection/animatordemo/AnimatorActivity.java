package com.zhangxq.democollection.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangxq on 16/2/2.
 */
public class AnimatorActivity extends Activity {
    @Bind(R.id.textView)
    TextView textView;

    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

        Animator animatorX = ObjectAnimator.ofFloat(textView, "translationX", 700, -150, 50, 0);
        Animator animator = ObjectAnimator.ofFloat(textView, "alpha", 0, 1, 1, 1);

        animatorSet.play(animatorX);
        animatorSet.play(animatorX).with(animator);
        animatorSet.setDuration(1000);
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        animatorSet.start();
    }
}
