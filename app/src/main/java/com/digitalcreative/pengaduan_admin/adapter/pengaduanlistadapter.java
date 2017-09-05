package com.digitalcreative.pengaduan_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.digitalcreative.pengaduan_admin.Model;
import com.digitalcreative.pengaduan_admin.R;

import java.util.List;

/**
 * Created by digitalcreative on 8/15/17.
 */

public class pengaduanlistadapter extends BaseAdapter {
    private Context context;
    private List<Model> list;

    public pengaduanlistadapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Model model = (Model)getItem(i);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem,null);
        }
        TextView tv_judul = (TextView)view.findViewById(R.id.judul_list);
        TextView tv_detail = (TextView)view.findViewById(R.id.detail_list);

        tv_judul.setText(model.getJudul());
        tv_detail.setText(model.getDetail());
        return view;
    }
}
