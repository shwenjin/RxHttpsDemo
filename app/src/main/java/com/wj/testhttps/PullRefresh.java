package com.wj.testhttps;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PullRefresh extends Activity {
    private RecyclerView mRecyclerView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(this));

//        RecyclerViewUtils.setHeaderView(mRecyclerView,new S);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }



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
