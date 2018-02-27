package com.wj.testhttps;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import jp.co.recruit_lifestyle.android.widget.WaveView;

public class RecycleActivity extends AppCompatActivity {
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout=null;
    private RecyclerView mRecyclerView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        mWaveSwipeRefreshLayout= (WaveSwipeRefreshLayout) findViewById(R.id.swipe);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(this));

        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(20);

                    }
                }.start();
            }
        });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mWaveSwipeRefreshLayout.setRefreshing(false);
        }
    };

    class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        private Context context;

        MyAdapter(Context context){
            this.context=context;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(context));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.textView.setText("1234");
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public TextView textView=null;

        public MyHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView;
        }
    }
}
