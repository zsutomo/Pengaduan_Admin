package com.digitalcreative.pengaduan_admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Cetak_activity extends AppCompatActivity {
    Context context;
    String[] pengaduan = {"Pengaduan 1","Pengaduan 2","Pengaduan 3","Pengaduan 4","Pengaduan 5"};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak_activity);
        //set up Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cetak Form");
        toolbar.setTitleTextColor(Color.WHITE);

        //Set List
        ListView listView = (ListView)findViewById(R.id.list_pengaduan2);
        ArrayAdapter adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pengaduan);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cetak_activity.this);
                builder.setMessage("Apakah Anda Ingin Mencetak Form ini?");
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Cetak_activity.this, "Berhasil di Print", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            });
    }
}
