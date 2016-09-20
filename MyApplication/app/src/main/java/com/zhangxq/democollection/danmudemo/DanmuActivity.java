
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
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;
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
    private Button mBtnSendDanmaku;
    private EditText editText;
    private DanmuControl danmuControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmuku);
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        editText = (EditText) findViewById(R.id.editText);
        mBtnSendDanmaku = (Button) findViewById(R.id.btn_send);
        mBtnSendDanmaku.setOnClickListener(this);
        danmuControl = new DanmuControl(this, mDanmakuView);
    }

    @Override
    public void onClick(View v) {
        String avator = "http://g.hiphotos.baidu.com/image/h%3D200/sign=9b2f9371992397ddc9799f046983b216/dc54564e9258d1094dc90324d958ccbf6c814d7a.jpg";
        String name = "张三";
        String content = editText.getText().toString().trim();
        if (content != null && content.length() > 0) {
            danmuControl.addDanmu(avator, name, content);
        } else {
            Toast.makeText(this, "发送内容为空", Toast.LENGTH_LONG).show();
        }
    }
}
