package com.zhangxq.democollection.imagescrolldemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zhangxq.democollection.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangq on 2016/3/4.
 */
public class ScrollImageActivity extends Activity {
    @Bind(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_image);
        ButterKnife.bind(this);

        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        final int windowWidth = wm.getDefaultDisplay().getWidth();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.action_bar_back) // ����ͼƬ�����ڼ���ʾ��ͼƬ
                .showImageForEmptyUri(R.drawable.action_bar_back) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
                .showImageOnFail(R.drawable.action_bar_back) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
                .cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)// �������ص�ͼƬ�Ƿ񻺴���SD����
                .build();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        ImageLoader.getInstance().displayImage("http://ww3.sinaimg.cn/bmiddle/67594de9jw1f1kw0drpn6j20m82c2qcg.jpg", imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                int imageWidth = bitmap.getWidth();
                int imageHeight = bitmap.getHeight();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                params.width = windowWidth;
                params.height = windowWidth * imageHeight / imageWidth;
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
}
