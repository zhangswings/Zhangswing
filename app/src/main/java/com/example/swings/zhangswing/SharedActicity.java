package com.example.swings.zhangswing;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.luckyandyzhang.cleverrecyclerview.CleverRecyclerView;

public class SharedActicity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    CleverRecyclerView cleverRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_info = (TextView) findViewById(R.id.tv_text);
                String info = tv_info.getText().toString().trim();
                editor.putString("info", info);
                boolean isSaved = editor.commit();
                if (isSaved) {
                    Toast.makeText(getApplicationContext(), "保存成功！\n保存信息：" + info, Toast.LENGTH_SHORT).show();
                }
            }
        });
        initData();
        adapter = new MyAdapter();
        cleverRecyclerView = (CleverRecyclerView) findViewById(R.id.crv_info);
        //设置方向
        cleverRecyclerView.setOrientation(RecyclerView.VERTICAL);
        //支持设置滚动动画的时长
        cleverRecyclerView.setScrollAnimationDuration(200);
//        支持设置触发滚动到下一页的阀值
        cleverRecyclerView.setSlidingThreshold(0.1f);
        cleverRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        cleverRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
        cleverRecyclerView.setAdapter(adapter);
//        cleverRecyclerView.set
        /**
         * 设置一页可以显示的item的数量
         * <p>注意：此方法必须在{@link CleverRecyclerView#setAdapter(Adapter)}之后调用
         *
         * @param visibleChildCount 目标数量
         */
        cleverRecyclerView.setVisibleChildCount(3);
    }

    private List<String> mDatas;

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private MyAdapter adapter;

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(SharedActicity.this).inflate(R.layout.reclclerview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.text.setHeight((int)(Math.random()*100));
            holder.text.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView text;

            public MyViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.item_tv);
            }
        }
    }

}
