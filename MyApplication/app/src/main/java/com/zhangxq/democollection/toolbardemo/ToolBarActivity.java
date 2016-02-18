package com.zhangxq.democollection.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangxq on 16/2/17.
 */
public class ToolBarActivity extends AppCompatActivity {

    @Bind(R.id.id_tool_bar)
    Toolbar toolbar;
    @Bind(R.id.imageButtonBack)
    ImageButton imageButtonBack;
    @Bind(R.id.textViewTitle)
    TextView textViewTitle;
    @Bind(R.id.imageButtonOption)
    ImageButton imageButtonOption;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick(value = {R.id.imageButtonBack, R.id.imageButtonOption})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonBack:
                onToolBarBackPress();
                break;
            case R.id.imageButtonOption:
                onToolBarOptionPress();
                break;
            default:
                break;
        }
    }

    /**
     * 返回按钮，重载使用
     */
    public void onToolBarBackPress() {
        finish();
    }

    /**
     * 右上角按钮，重载使用
     */
    public void onToolBarOptionPress() {

    }

    /**
     * 设置标题栏
     *
     * @param resId
     */
    public void setTitle(int resId) {
        textViewTitle.setText(resId);
    }

    /**
     * 设置标题栏
     *
     * @param title
     */
    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    /**
     * 设置返回按钮图标
     *
     * @param resId
     */
    public void setButtonBackImage(int resId) {
        imageButtonBack.setImageResource(resId);
    }

    /**
     * 设置右上角按钮图标
     *
     * @param resId
     */
    public void setButtonOptionImage(int resId) {
        imageButtonOption.setImageResource(resId);
    }
}
