package com.digitalcreative.pengaduan_admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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
        getSupportActionBar().setTitle("Cetak_Admin");

        //Set List
        ListView listView = (ListView)findViewById(R.id.list_pengaduan2);
        ArrayAdapter adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pengaduan);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            openDialog();
            }
        });
    }

//    private void showDialog() {
//        AlertDialog.Builder builder = new AlertDialog().Builder(this);
//        builder.setMessage("Write here your message");
//        builder.setCancelable(true);
//
//        builder.setPositiveButton(
//                "Yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        builder.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//        AlertDialog allert = builder.create();
//        allert.show();
//    }

    public void openDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.pop_up);
        dialog.setTitle("Alert Dialog");
        dialog.show();
    }
}
