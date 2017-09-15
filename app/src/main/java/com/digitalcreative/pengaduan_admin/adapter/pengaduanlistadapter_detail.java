package com.digitalcreative.pengaduan_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.digitalcreative.pengaduan_admin.Model;
import com.digitalcreative.pengaduan_admin.R;

import java.util.List;

/**
 * Created by digitalcreative on 8/15/17.
 */

public class pengaduanlistadapter_detail extends BaseAdapter {
    private Context context;
    private List<Model> list;

    public pengaduanlistadapter_detail(Context context, List<Model> list) {
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
            view = layoutInflater.inflate(R.layout.listitem_detail,null);
        }
//        TextView tv_judul = view.findViewById(R.id.tanggal_detail);
//        TextView tv_detail = view.findViewById(R.id.detail_list);

//        tv_judul.setText(model.getTanggal());
//        tv_detail.setText(model.getDetail());
        return view;
    }
}
