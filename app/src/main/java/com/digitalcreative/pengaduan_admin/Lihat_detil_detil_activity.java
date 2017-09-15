package com.digitalcreative.pengaduan_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;

import java.util.List;

public class Lihat_detil_detil_activity extends AppCompatActivity {
    private ListView listView;
    private List<Model> list;
    private pengaduanlistadapter listadapter;
    private String judul;
    private String detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pengaduan_formview);

        //Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Form Pengaduan");

    }
    }
