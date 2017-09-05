package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.digitalcreative.pengaduan_admin.R;
import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class Lihat_detil_Activity extends AppCompatActivity {
    private ListView listView;
    private List<Model> list;
    private pengaduanlistadapter listadapter;
    private String judul;
    private String detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat__detil_);

        //Set Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detil 1");

        //Set List
        listView = (ListView)findViewById(R.id.list_pengaduan2);
        initData();
        listadapter = new pengaduanlistadapter(getApplicationContext(),list);
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
        judul = getResources().getString(R.string.Judul_list1);
        detail = getResources().getString(R.string.detail_list1);
        model = new Model(judul,detail);
        list.add(model);

        judul = getResources().getString(R.string.Judul_list2);
        detail = getResources().getString(R.string.detail_list2);
        model = new Model(judul,detail);
        list.add(model);

        judul = getResources().getString(R.string.Judul_list3);
        detail = getResources().getString(R.string.detail_list3);
        model = new Model(judul,detail);
        list.add(model);

    }
    }
