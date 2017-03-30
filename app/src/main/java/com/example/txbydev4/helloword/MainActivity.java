package com.example.txbydev4.helloword;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.txbydev4.helloword.services.AidlService;
import com.lib.wlib.adapter.CommonAdapter;
import com.lib.wlib.adapter.ViewHolder;
import com.lib.wlib.intent.IntentTool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionMenuView amenu;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("111111", "onCreate");
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, new HelloJni().sayHello(), Toast.LENGTH_SHORT).show();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        amenu = (ActionMenuView) findViewById(R.id.amv_search);
        toolbar.setTitle("asdasd");
        amenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //Toast.makeText(MainActivity.this, item.getItemId()+"", Toast.LENGTH_LONG).show();
                return onOptionsItemSelected(item);
            }
        });
        List<Bean> beans = new ArrayList<Bean>();
        for (int i = 0; i < 50; i++) {
            beans.add(new Bean("head" + i, "body" + i));
        }
        ListView listView = (ListView) findViewById(R.id.abc);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(IntentTool.makeIntent(MainActivity.this, RecyleListActivity.class));
            }
        });
        listView.setAdapter(new CommonAdapter<Bean>(this, beans, R.layout.layout_listview) {
            @Override
            public void convert(ViewHolder holder, Bean item) {
                holder.setTextView(R.id.a, item.head).setTextView(R.id.b, item.body);
            }
        });
        i = new Intent(this, AidlService.class);
        startService(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, amenu.getMenu());
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(MainActivity.this, item.getItemId()+"", Toast.LENGTH_LONG).show();
        if(R.id.first1 == item.getItemId()) {
            startActivity(IntentTool.makeIntent(MainActivity.this, ServiceActivity.class));
        }
        if(R.id.first2 == item.getItemId()) {
            startActivity(IntentTool.makeIntent(MainActivity.this, MyServiceActivity.class));
        }
        if(R.id.first3 == item.getItemId()) {
            startActivity(IntentTool.makeIntent(MainActivity.this, HandlerActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(i);
        Log.d("111111", "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("111111", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("111111", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("111111", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("111111", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("111111", "onStop");
    }


}
