
package com.zhangxq.democollection.danmudemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import master.flame.danmaku.danmaku.model.android.SimpleTextCacheStuffer;
import master.flame.danmaku.danmaku.util.SystemClock;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.VideoView;

import com.zhangxq.democollection.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.danmaku.util.IOUtils;

public class DanmuActivity extends Activity implements View.OnClickListener {

    private IDanmakuView mDanmakuView;
    private View mMediaController;
    private Button mBtnRotate;
    private Button mBtnHideDanmaku;
    private Button mBtnShowDanmaku;
    private Button mBtnPauseDanmaku;
    private Button mBtnResumeDanmaku;
    private Button mBtnSendDanmaku;
    private Button mBtnSendDanmakuTextAndImage;
    private Button mBtnSendDanmakus;

    private DanmuControl danmuControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmuku);
        mMediaController = findViewById(R.id.media_controller);
        mBtnRotate = (Button) findViewById(R.id.rotate);
        mBtnHideDanmaku = (Button) findViewById(R.id.btn_hide);
        mBtnShowDanmaku = (Button) findViewById(R.id.btn_show);
        mBtnPauseDanmaku = (Button) findViewById(R.id.btn_pause);
        mBtnResumeDanmaku = (Button) findViewById(R.id.btn_resume);
        mBtnSendDanmaku = (Button) findViewById(R.id.btn_send);
        mBtnSendDanmakuTextAndImage = (Button) findViewById(R.id.btn_send_image_text);
        mBtnSendDanmakus = (Button) findViewById(R.id.btn_send_danmakus);
        mBtnRotate.setOnClickListener(this);
        mBtnHideDanmaku.setOnClickListener(this);
        mMediaController.setOnClickListener(this);
        mBtnShowDanmaku.setOnClickListener(this);
        mBtnPauseDanmaku.setOnClickListener(this);
        mBtnResumeDanmaku.setOnClickListener(this);
        mBtnSendDanmaku.setOnClickListener(this);
        mBtnSendDanmakuTextAndImage.setOnClickListener(this);
        mBtnSendDanmakus.setOnClickListener(this);
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);

        danmuControl = new DanmuControl(this);
        danmuControl.setDanmakuView(mDanmakuView);
    }

    @Override
    public void onClick(View v) {
        if (v == mMediaController) {
            mMediaController.setVisibility(View.GONE);
        }
        if (mDanmakuView == null || !mDanmakuView.isPrepared())
            return;
        if (v == mBtnRotate) {
            setRequestedOrientation(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (v == mBtnHideDanmaku) {
            mDanmakuView.hide();
        } else if (v == mBtnShowDanmaku) {
            mDanmakuView.show();
        } else if (v == mBtnPauseDanmaku) {
            mDanmakuView.pause();
        } else if (v == mBtnResumeDanmaku) {
            mDanmakuView.resume();
        } else if (v == mBtnSendDanmaku) {
            Danmu danmu = new Danmu(0, "", "张三", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
            danmuControl.addDanmu(danmu);
        } else if (v == mBtnSendDanmakus) {
            Boolean b = (Boolean) mBtnSendDanmakus.getTag();
            timer.cancel();
            if (b == null || !b) {
                mBtnSendDanmakus.setText(R.string.cancel_sending_danmakus);
                timer = new Timer();
                timer.schedule(new AsyncAddTask(), 0, 1000);
                mBtnSendDanmakus.setTag(true);
            } else {
                mBtnSendDanmakus.setText(R.string.send_danmakus);
                mBtnSendDanmakus.setTag(false);
            }
        }
    }

    Timer timer = new Timer();

    class AsyncAddTask extends TimerTask {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                Danmu danmu = new Danmu(0, "", "张三", "我");
                danmuControl.addDanmu(danmu);
                SystemClock.sleep(20);
            }
        }
    }
}
