package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;
import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter_detail;
import com.digitalcreative.pengaduan_admin.model.Model_Pengaduan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class List_Pengaduan_Detail extends AppCompatActivity {
    private ListView listView;
    private List<Model_Pengaduan> list;
    private pengaduanlistadapter_detail listadapter;
    private String tanggal;
    private String detail;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pengaduan_detail);

        //Set Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detil Pengaduan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);

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

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), List_Pengaduan.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        final String penyulang=extras.getString("penyulang");
        list = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pengaduan").child(penyulang).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Model_Pengaduan model_pengaduan=new Model_Pengaduan();
                    String tanggal=postSnapshot.child("tanggal").getValue().toString() ;
                    String nama =postSnapshot.child("petugas").getValue().toString();
                    String kode_gardu=postSnapshot.child("kode_gardu").getValue().toString() ;
                    String daya =postSnapshot.child("daya").getValue().toString();
                    String alamat=postSnapshot.child("alamat").getValue().toString() ;
                    String uid=postSnapshot.getKey() ;
                    model_pengaduan.setAlamat(alamat);
                    model_pengaduan.setDaya(daya);
                    model_pengaduan.setKode_gardu(kode_gardu);
                    model_pengaduan.setTanggal(tanggal);
                    model_pengaduan.setNama(nama);
                    model_pengaduan.setUid(uid);
                    list.add(model_pengaduan);
                }
                listadapter = new pengaduanlistadapter_detail(getApplicationContext(),list);
                listView.setAdapter(listadapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent =  new Intent(getApplicationContext(), Lihat_detil_detil_activity.class);
                        intent.putExtra("uid",list.get(i).getUid());
                        intent.putExtra("penyulang",penyulang);
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
