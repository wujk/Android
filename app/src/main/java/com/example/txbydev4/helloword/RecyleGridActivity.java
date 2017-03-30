package com.example.txbydev4.helloword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lib.wlib.adapter.CommonRecyleAdapter;
import com.lib.wlib.adapter.OnItemClickLitener;
import com.lib.wlib.adapter.RecyleViewHolder;
import com.lib.wlib.intent.IntentTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txbydev4 on 17/3/3.
 */

public class RecyleGridActivity extends AppCompatActivity {
    private RecyclerView view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyle);
        final List<Bean> beans = new ArrayList<Bean>();
        for (int i = 0; i < 50; i++) {
            beans.add(new Bean("head" + i, "body" + i));
        }
        view = (RecyclerView)findViewById(R.id.id_recyclerview);
        view.setLayoutManager(new GridLayoutManager(this, 4));
        view.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        CommonRecyleAdapter adapter =new CommonRecyleAdapter<Bean>(this, beans, R.layout.layout_listview){
            @Override
            public void convert(RecyleViewHolder holder, Bean item) {
                holder.setTextView(R.id.a, item.head).setTextView(R.id.b, item.body);
            }
        };
        adapter.addItemClickListener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyleGridActivity.this, beans.get(position).body, Toast.LENGTH_LONG).show();
                startActivity(IntentTool.makeIntent(RecyleGridActivity.this, MyTouchActivity.class));
            }
        });
        view.setAdapter(adapter);
    }
}
