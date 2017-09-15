package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter_detail;

import java.util.ArrayList;
import java.util.List;

public class List_Pengaduan_Detail extends AppCompatActivity {
    private ListView listView;
    private List<Model> list;
    private pengaduanlistadapter_detail listadapter;
    private String tanggal;
    private String detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pengaduan_detail);

        //Set Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detil Pengaduan");

        //Set List
        listView = (ListView)findViewById(R.id.list_pengaduan2);
        initData();
        listadapter = new pengaduanlistadapter_detail(getApplicationContext(),list);
        listView.setAdapter(listadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(getApplicationContext(), Lihat_detil_detil_activity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Model model;
        list = new ArrayList<>();
        tanggal = getResources().getString(R.string.Detail_list_tanggal);
        detail = getResources().getString(R.string.detail_list1);
        model = new Model(tanggal,detail);
        list.add(model);

        tanggal = getResources().getString(R.string.Judul_list2);
        detail = getResources().getString(R.string.detail_list2);
        model = new Model(tanggal,detail);
        list.add(model);

        tanggal = getResources().getString(R.string.Judul_list3);
        detail = getResources().getString(R.string.detail_list3);
        model = new Model(tanggal,detail);
        list.add(model);

    }
    }
