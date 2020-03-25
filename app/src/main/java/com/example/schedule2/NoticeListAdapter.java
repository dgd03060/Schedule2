package com.example.schedule2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter{
    private Context context;
    private List<Notice> noticedList;

    public NoticeListAdapter(Context context, List<Notice> noticedList) {
        this.context = context;
        this.noticedList = noticedList;
    }

    @Override
    public int getCount() {
        return noticedList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticedList.get(position);//해당 위치의 값을 리스트뷰에 뿌려줌
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice, null);


        TextView noticeText = (TextView)v.findViewById(R.id.noticeText);
        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);
        noticeText.setText(noticedList.get(position).getNotice());
        nameText.setText(noticedList.get(position).getName());
        dateText.setText(noticedList.get(position).getDate());
        v.setTag(noticedList.get(position).getNotice());
        return v;
    }
}
