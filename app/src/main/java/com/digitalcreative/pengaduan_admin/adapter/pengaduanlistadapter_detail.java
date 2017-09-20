package com.digitalcreative.pengaduan_admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digitalcreative.pengaduan_admin.Model;
import com.digitalcreative.pengaduan_admin.R;
import com.digitalcreative.pengaduan_admin.model.Model_Pengaduan;

import java.util.List;

/**
 * Created by digitalcreative on 8/15/17.
 */

public class pengaduanlistadapter_detail extends BaseAdapter {
    private Context context;
    private List<Model_Pengaduan> list;

    public pengaduanlistadapter_detail(Context context, List<Model_Pengaduan> list) {
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
        Model_Pengaduan model_pengaduan = (Model_Pengaduan)getItem(i);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listitem_detail,null);
        }

        TextView tanggal = view.findViewById(R.id.tanggal_detail);
        TextView nama_text_view = view.findViewById(R.id.nama_text_view);
        TextView kode_gardu_text_view = view.findViewById(R.id.kode_gardu_text_view);
        TextView daya_text_view = view.findViewById(R.id.daya_text_view);
        TextView alamat_text_view = view.findViewById(R.id.alamat_text_view);

        tanggal.setText(model_pengaduan.getTanggal());
        nama_text_view.setText(model_pengaduan.getNama());
        kode_gardu_text_view.setText(model_pengaduan.getKode_gardu());
        daya_text_view.setText(model_pengaduan.getDaya());
        alamat_text_view.setText(model_pengaduan.getAlamat());
        return view;
    }
}
