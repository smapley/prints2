package com.smapley.prints2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.prints2.R;
import com.smapley.prints2.adapter.Detail2Adapter;
import com.smapley.prints2.http.params.GetMingxiqParams;
import com.smapley.prints2.http.service.GetMingxiqService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by smapl on 2016/4/11.
 */
@ContentView(R.layout.activity_detail2)
public class Detail2 extends Activity {

    @ViewInject(R.id.title_item2)
    private TextView title_item2;

    @ViewInject(R.id.page_up)
    private TextView page_up;
    @ViewInject(R.id.page_down)
    private TextView page_down;
    @ViewInject(R.id.page_num)
    private TextView page_num;

    @ViewInject(R.id.listView)
    private ListView listView;

    private List<Map<String, String>> listData = new ArrayList<>();
    private List<Map<String, String>> listData_now = new ArrayList<>();
    private Detail2Adapter adapter;

    private int pageNum = 1;


    private int PageSize = 40;
    private GetMingxiqService getMingxiqService = new GetMingxiqService() {
        @Override
        public void Succ(String data) {
            Log.e("result",data);
            Map<String,String> map=JSON.parseObject(data,new TypeReference<Map<String, String>>(){});
            title_item2.setText(map.get("allgold"));
            listData = JSON.parseObject(map.get("result"), new TypeReference<List<Map<String, String>>>() {
            });
            if (listData.size() > PageSize) {
                listData_now.clear();
                listData_now.addAll(listData.subList(0, PageSize));
            } else {
                listData_now.clear();
                listData_now.addAll(listData);
            }
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        page_num.setText(pageNum + "");

        adapter = new Detail2Adapter(this, listData_now);
        listView.setAdapter(adapter);

        getMingxiqService.load(new GetMingxiqParams(getIntent().getStringExtra("zhang"), getIntent().getStringExtra("qihao")));
    }

    @Event({R.id.page_down,R.id.page_up})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.page_down:
                if (pageNum * PageSize < listData.size()) {
                    if (pageNum * PageSize + PageSize < listData.size()) {
                        listData_now.clear();
                        listData_now.addAll(listData.subList(pageNum * PageSize, pageNum * PageSize + PageSize));
                    } else {
                        listData_now.clear();
                        listData_now .addAll(listData.subList(pageNum * PageSize, listData.size()));
                    }
                    pageNum++;
                    page_num.setText(pageNum + "");
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.page_up:
                if (pageNum > 1) {
                    pageNum--;
                    listData_now.clear();
                    listData_now .addAll(listData.subList(pageNum * PageSize - PageSize, pageNum * PageSize));
                    page_num.setText(pageNum + "");
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
