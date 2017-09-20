package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class List_Pengaduan extends AppCompatActivity {
    private ListView listView;
    private List<Model> list;
    private pengaduanlistadapter listadapter;
    private String judul;
    private String detail;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pengaduan);

        //Set Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Pengaduan");

        //Set List
        listView = (ListView)findViewById(R.id.list_pengaduan);
        initData();

    }

    private void initData() {
        list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pengaduan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Model model;

                    judul = postSnapshot.getKey();
                    detail = String.valueOf(postSnapshot.getChildrenCount());
                    model = new Model(judul,detail);
                    System.out.println(judul);
                    list.add(model);
                }
                listadapter = new pengaduanlistadapter(getApplicationContext(),list);
                listView.setAdapter(listadapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        System.out.println(list.get(i).getJudul());
                        Intent intent =  new Intent(getApplicationContext(), List_Pengaduan_Detail.class);
                        intent.putExtra("penyulang",list.get(i).getJudul());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
