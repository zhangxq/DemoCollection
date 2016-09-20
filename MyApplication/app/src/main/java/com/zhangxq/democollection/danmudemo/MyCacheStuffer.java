package com.zhangxq.democollection.danmudemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;

import com.zhangxq.democollection.utils.Util;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;

public class MyCacheStuffer extends BaseCacheStuffer {
    private int AVATAR_DIAMETER;  //头像直径 单位：像素
    private int AVATAR_PADDING;
    private int TEXT_LEFT_PADDING;
    private int TEXT_RIGHT_PADDING;
    private int TEXT_SIZE;
    private int TEXT_TOP_PADDING; // 文字距离屏幕顶端的距离差值

    private static final int NICK_COLOR = 0xffff1874;//昵称 红色
    private static final int NICK_SHADOW_COLOR = 0x010101;//昵称 阴影
    private static final int TEXT_COLOR = 0xffeeeeee;  //文字内容  白色
    private int LIGHT_GREY_COLOR = 0x66000000;
    private int DANMU_RADIUS;

    private Context context;

    public MyCacheStuffer(Context context) {
        this.context = context;
        AVATAR_DIAMETER = Util.DensityUtil.dip2px(context, 33);
        AVATAR_PADDING = Util.DensityUtil.dip2px(context, 1);
        TEXT_LEFT_PADDING = Util.DensityUtil.dip2px(context, 2);
        TEXT_RIGHT_PADDING = Util.DensityUtil.dip2px(context, 8);
        TEXT_SIZE = Util.DensityUtil.dip2px(context, 13);
        DANMU_RADIUS = Util.DensityUtil.dip2px(context, 8);
        TEXT_TOP_PADDING = Util.DensityUtil.dip2px(context, 14);
    }

    @Override
    public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
        paint.setTextSize(TEXT_SIZE);
        float w = 0;
        for (String tempStr : danmaku.text.toString().split(":")) {
            if (tempStr.length() > 0) {
                w = Math.max(paint.measureText(tempStr), w);
            }
        }
        danmaku.paintWidth = w + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING + TEXT_RIGHT_PADDING;
        danmaku.paintHeight = AVATAR_DIAMETER + AVATAR_PADDING * 2;
    }

    @Override
    public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
    }

    @Override
    public void drawText(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, TextPaint paint, boolean fromWorkerThread) {
        String texts = danmaku.text.toString();
        String[] text = texts.split(":");
        paint.setColor(Color.WHITE);
        if (danmaku.tag != null) {
            canvas.drawCircle(AVATAR_DIAMETER / 2 + AVATAR_PADDING, AVATAR_DIAMETER / 2 + AVATAR_PADDING, AVATAR_DIAMETER / 2 + AVATAR_PADDING, paint);
            canvas.drawBitmap((Bitmap) danmaku.tag, null, new RectF(left + AVATAR_PADDING, AVATAR_PADDING, left + AVATAR_PADDING + AVATAR_DIAMETER, AVATAR_DIAMETER + AVATAR_PADDING), paint);
        }
        paint.setTextSize(TEXT_SIZE);
        
        paint.setColor(NICK_COLOR);
        paint.setShadowLayer(1, 1, 4, NICK_SHADOW_COLOR);
        canvas.drawText(text[0], left + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING, top - TEXT_TOP_PADDING + AVATAR_PADDING, paint);
        paint.setColor(TEXT_COLOR);
        paint.clearShadowLayer();
        canvas.drawText(text[1], left + AVATAR_DIAMETER + AVATAR_PADDING * 2 + TEXT_LEFT_PADDING, top - TEXT_TOP_PADDING + AVATAR_PADDING + AVATAR_DIAMETER / 2, paint);
    }

    @Override
    public void clearCaches() {
    }

    @Override
    public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
        String[] text = danmaku.text.toString().split(":");
        Paint paint = new Paint();
        paint.setTextSize(TEXT_SIZE);
        float textWidth = paint.measureText(text[1]);
        paint.setColor(LIGHT_GREY_COLOR);
        paint.setAntiAlias(true);
        float danmuBgWidth = AVATAR_DIAMETER / 2 + AVATAR_PADDING + TEXT_LEFT_PADDING + textWidth + TEXT_RIGHT_PADDING;
        canvas.drawRoundRect(new RectF(AVATAR_DIAMETER / 2 + AVATAR_PADDING, top + AVATAR_DIAMETER / 2 + AVATAR_PADDING, AVATAR_DIAMETER / 2 + AVATAR_PADDING + danmuBgWidth, top + AVATAR_DIAMETER + AVATAR_PADDING), DANMU_RADIUS, DANMU_RADIUS, paint);
    }
}
