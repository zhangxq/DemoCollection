package com.zhangxq.democollection.lifeCycledemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhangxq.democollection.R;

public class LifeCycleActivity extends Activity implements View.OnClickListener {
    private Button button;
    private Button buttonPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.d("LifeCycleActivity", "onCreate run..");

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        buttonPage2 = (Button) findViewById(R.id.buttonPage2);
        buttonPage2.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycleActivity", "onRestart run..");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycleActivity", "onStart run..");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycleActivity", "onResume run..");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycleActivity", "onPause run..");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycleActivity", "onStop run..");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycleActivity", "onDestroy run..");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
            builder.setTitle("提示"); //设置标题
            builder.setMessage("是否确认退出?"); //设置内容
            builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss(); //关闭dialog
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //参数都设置完成了，创建并显示出来
            builder.create().show();
        } else {
            Intent intent = new Intent(this, LifeCyclePage2Activity.class);
            startActivity(intent);
        }
    }
}
