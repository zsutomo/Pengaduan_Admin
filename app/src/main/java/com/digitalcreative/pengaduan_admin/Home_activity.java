package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        //set up Toolbar
//        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Admin");

        //Sett CallFragment
        Button button_lihat = (Button)findViewById(R.id.btn_lihat);
        button_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),List_Pengaduan.class);
                startActivity(intent);
            }
        });

        Button button_cetak = (Button) findViewById(R.id.btn_cetak);
        button_cetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(),Cetak_activity.class);
                startActivity(intent);
            }
        });

    }
}
