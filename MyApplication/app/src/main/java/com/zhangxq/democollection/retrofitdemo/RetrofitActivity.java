package com.zhangxq.democollection.retrofitdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhangxq.democollection.R;
import com.zhangxq.democollection.utils.ACache;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zhangxq on 15/7/30.
 */

public class RetrofitActivity extends Activity {

    private List<Contributor> contributors;
    private ACache aCache;

    @Bind(R.id.listView)
    ListView listView;

    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        contributors = new ArrayList<Contributor>();
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);
        aCache = ACache.get(this);
        getData();
    }

    private void getData() {
        contributors = (List<Contributor>) aCache.getAsObject("contributors");
        if (contributors != null) {
            adapter.notifyDataSetChanged();
        } else {
            contributors = new ArrayList<Contributor>();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(GsonConverterFactory.create()).build();
        final RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        new Thread() {
            @Override
            public void run() {
                Call<List<Contributor>> call = retrofitService.contributors("square", "retrofit");
                Log.e("call", call.toString());
                call.enqueue(new Callback<List<Contributor>>() {
                    @Override
                    public void onResponse(Response<List<Contributor>> response) {
                        contributors = response.body();
                        if (contributors != null && contributors.size() > 0) {
                            ACache.get(RetrofitActivity.this).put("contributors", (Serializable) contributors);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("error", t.toString());
                    }
                });
            }
        }.start();
    }

    private class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contributors.size();
        }

        @Override
        public Contributor getItem(int i) {
            return contributors.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = View.inflate(RetrofitActivity.this, R.layout.view_contributor_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textViewLogin = (TextView) view.findViewById(R.id.textViewLogin);
                viewHolder.textViewContributor = (TextView) view.findViewById(R.id.textViewContributor);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Contributor contributor = getItem(i);
            viewHolder.textViewLogin.setText(contributor.getLogin());
            viewHolder.textViewContributor.setText(contributor.getContributions() + "");

            return view;
        }

        private class ViewHolder {
            public TextView textViewLogin;
            public TextView textViewContributor;
        }
    }
}
