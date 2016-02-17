package com.zhangxq.democollection.progressdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.zhangxq.democollection.R;

/**
 *
 */
public class ProgressActivity extends Activity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int progress = progressBar.getProgress();
            progressBar.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeCallbacksAndMessages(null);
            } else {
                mHandler.sendEmptyMessageDelayed(0, 1000);
                Log.e("mHandler", "mHandler" + progress);
            }
        }
    };
    private HorizentalProgressBarWithNumber progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = (HorizentalProgressBarWithNumber) findViewById(R.id.hpbw);
        mHandler.sendEmptyMessage(0);
    }

}
