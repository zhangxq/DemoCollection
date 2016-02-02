package com.zhangxq.democollection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhangxq.democollection.androidwheeldemo.AndroidWheelActivity;
import com.zhangxq.democollection.animatordemo.AnimatorActivity;
import com.zhangxq.democollection.retrofitdemo.RetrofitActivity;
import com.zhangxq.democollection.toucheventdemo.TouchEventActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    @Bind(R.id.listView)
    ListView listView;

    String[] items = new String[]{"retrofitDemo", "TouchEventDemo", "androidWheelDemo", "animatorDemo"};
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new ListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent = new Intent(this, RetrofitActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, TouchEventActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, AndroidWheelActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, AnimatorActivity.class);
                startActivity(intent3);
            default:
                break;
        }
    }

    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public String getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView != null) {
                view = convertView;
            } else {
                view = View.inflate(MainActivity.this, R.layout.main_list_item, null);
            }

            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText(getItem(position));

            return view;
        }
    }
}
