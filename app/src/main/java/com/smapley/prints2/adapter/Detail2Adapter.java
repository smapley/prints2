package com.smapley.prints2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smapley.prints2.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hao on 2015/11/9.
 */
public class Detail2Adapter extends BaseAdapter {

    private List<Map<String, String>> list;
    private LayoutInflater inflater;
    private Context context;

    public Detail2Adapter(Context context, List<Map<String, String>> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Map<String, String> map = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.detail2_item, null);
            viewHolder = new ViewHolder();
            viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            viewHolder.num = (TextView) convertView.findViewById(R.id.detail_item_num);
            viewHolder.gold = (TextView) convertView.findViewById(R.id.detail_item_gold);
            viewHolder.pei = (TextView) convertView.findViewById(R.id.detail_item_pei);
            viewHolder.zt = (TextView) convertView.findViewById(R.id.detail_item_zt);
            viewHolder.allid = (TextView) convertView.findViewById(R.id.detail_item_allid);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            if (map.get("hotstat").toString().equals("1")) {
                viewHolder.layout.setBackgroundColor(Color.YELLOW);
            } else {
                viewHolder.layout.setBackgroundColor(Color.WHITE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.num.setText(map.get("number"));
        viewHolder.gold.setText(map.get("gold"));
        viewHolder.pei.setText(map.get("pei"));
        switch (Integer.parseInt(map.get("zt"))) {
            case 0:
                viewHolder.zt.setText("未打印");
                break;
            case 1:
                viewHolder.zt.setText("已打印");

                break;
            case 9:
                viewHolder.zt.setText("已退码");

                break;
        }
        if (map.get("zt").equals("9")) {
            viewHolder.num.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.gold.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.pei.setTextColor(context.getResources().getColor(R.color.red));
            viewHolder.zt.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            viewHolder.num.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.gold.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.pei.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.zt.setTextColor(context.getResources().getColor(R.color.black));
        }
        return convertView;
    }

    public class ViewHolder {
        LinearLayout layout;
        TextView num;
        TextView gold;
        TextView pei;
        TextView zt;
        TextView allid;
    }
}
