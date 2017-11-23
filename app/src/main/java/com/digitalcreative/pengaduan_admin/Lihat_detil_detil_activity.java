package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;
import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter_detail;
import com.digitalcreative.pengaduan_admin.model.Model_Pengaduan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Lihat_detil_detil_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pengaduan_formview);

        //Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Pengaduan");
        toolbar.setTitleTextColor(Color.WHITE);

        Bundle extras = getIntent().getExtras();
        String uid=extras.getString("uid");
        String penyulang=extras.getString("penyulang");
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("pengaduan").child(penyulang).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                String tanggal=dataSnapshot.child("tanggal").getValue().toString();
                String alamat=dataSnapshot.child("alamat").getValue().toString();
                //first
                String petugas=dataSnapshot.child("petugas").getValue().toString() ;
                String kapel=dataSnapshot.child("kapel").getValue().toString() ;
                String kode_gardu=dataSnapshot.child("kode_gardu").getValue().toString() ;
                //spinner
                String penyulang=dataSnapshot.child("penyulang").getValue().toString();
                //=====
                String daya=dataSnapshot.child("daya").getValue().toString() ;
                String merk=dataSnapshot.child("merk").getValue().toString() ;
                String noserie=dataSnapshot.child("noserie").getValue().toString() ;
                String jlh_fasa=dataSnapshot.child("jlh_fasa").getValue().toString() ;
                String tap_operasi=dataSnapshot.child("tap_operasi").getValue().toString() ;
                String kons_trafo=dataSnapshot.child("kons_trafo").getValue().toString() ;
                //button ya tidak
                String rekondisi=dataSnapshot.child("rekondisi").getValue().toString();
                //=====
                String tahun_pembuatan=dataSnapshot.child("tahun_pembuatan").getValue().toString() ;
                //button ya tidak
                String kunci_gardu=dataSnapshot.child("kunci_gardu").getValue().toString();
                //=====
                //button ya tidak
                String kokon=dataSnapshot.child("kokon").getValue().toString();
                //=====
                //button angka 3 2 1 0
                String schoon_trafo=dataSnapshot.child("schoon_trafo").getValue().toString();
                //=====
                String kebutuhan_schoon_PHB =dataSnapshot.child("kebutuhan_schoon_PHB").getValue().toString();;
                //Data Pengaman Trafo
                String saklar_utama_phb=dataSnapshot.child("saklar_utama_phb").getValue().toString() ;
                String primer_phasa_r=dataSnapshot.child("primer_phasa_r").getValue().toString() ;
                String primer_phasa_s=dataSnapshot.child("primer_phasa_s").getValue().toString() ;
                String primer_phasa_t=dataSnapshot.child("primer_phasa_t").getValue().toString() ;

                String saklar_merk=dataSnapshot.child("saklar_merk").getValue().toString() ;
                String saklar_arus=dataSnapshot.child("saklar_arus").getValue().toString() ;

                String fuse_jurusan=dataSnapshot.child("fuse_jurusan").getValue().toString() ;
                final String fuse_R_Jur_A=dataSnapshot.child("fuse_R_Jur_A").getValue().toString() ;
                final String fuse_S_Jur_A=dataSnapshot.child("fuse_S_Jur_A").getValue().toString() ;
                final String fuse_T_Jur_A=dataSnapshot.child("fuse_T_Jur_A").getValue().toString() ;
                final String fuse_R_Jur_B=dataSnapshot.child("fuse_R_Jur_B").getValue().toString() ;
                final String fuse_S_Jur_B=dataSnapshot.child("fuse_S_Jur_B").getValue().toString() ;
                final String fuse_T_Jur_B=dataSnapshot.child("fuse_T_Jur_B").getValue().toString() ;
                String fuse_R_Jur_C=dataSnapshot.child("fuse_R_Jur_C").getValue().toString() ;
                String fuse_S_Jur_C=dataSnapshot.child("fuse_S_Jur_C").getValue().toString() ;
                String fuse_T_Jur_C=dataSnapshot.child("fuse_T_Jur_C").getValue().toString() ;
//                String fuse_R_Jur_D=dataSnapshot.child("fuse_R_Jur_D").getValue().toString() ;
//                String fuse_S_Jur_D=dataSnapshot.child("fuse_S_Jur_D").getValue().toString() ;
//                String fuse_T_Jur_D=dataSnapshot.child("fuse_T_Jur_D").getValue().toString() ;

                String nh_jurusan=dataSnapshot.child("nh_jurusan").getValue().toString() ;
                final String NH_R_Jur_A=dataSnapshot.child("nh_R_Jur_A").getValue().toString() ;
                final String NH_S_Jur_A =dataSnapshot.child("nh_S_Jur_A").getValue().toString();
                final String NH_T_Jur_A=dataSnapshot.child("nh_T_Jur_A").getValue().toString() ;
                final String NH_R_Jur_B=dataSnapshot.child("nh_R_Jur_B").getValue().toString() ;
                final String NH_S_Jur_B=dataSnapshot.child("nh_S_Jur_B").getValue().toString() ;
                final String NH_T_Jur_B=dataSnapshot.child("nh_T_Jur_B").getValue().toString() ;
                final String NH_R_Jur_C=dataSnapshot.child("nh_R_Jur_C").getValue().toString() ;
                final String NH_S_Jur_C=dataSnapshot.child("nh_S_Jur_C").getValue().toString() ;
                final String NH_T_Jur_C=dataSnapshot.child("nh_T_Jur_C").getValue().toString() ;
                String NH_R_Jur_D=dataSnapshot.child("nh_R_Jur_D").getValue().toString() ;
                String NH_S_Jur_D=dataSnapshot.child("nh_S_Jur_D").getValue().toString() ;
                String NH_T_Jur_D=dataSnapshot.child("nh_T_Jur_D").getValue().toString() ;

                String arrester=dataSnapshot.child("arrester").getValue().toString() ;
                //===============
                //Data Pertahanan
                String saklar_tahanan_arrester=dataSnapshot.child("saklar_tahanan_arrester").getValue().toString();
                String saklar_tahanan_netral=dataSnapshot.child("saklar_tahanan_netral").getValue().toString() ;
                String body_trafo=dataSnapshot.child("body_trafo").getValue().toString() ;
                //=============
                //Data Kabel
                //button nyy lvtc nyfgby
                String kabel_inlet=dataSnapshot.child("kabel_inlet").getValue().toString();
                //=====
                //button 70 95 150 240
                String penampang_inlet=dataSnapshot.child("penampang_inlet").getValue().toString();
                //=====


                //Jenis Kabel Outlet
                String data_kabel_jurusan=dataSnapshot.child("data_kabel_jurusan").getValue().toString();
                //button jnyy lvtc nyfgby
                String jenis_kabel_jurusan_a=dataSnapshot.child("jenis_kabel_jurusan_a").getValue().toString();
                //=====
                //button 25 35 70 95 150
                String penampang_jurusan_a=dataSnapshot.child("penampang_jurusan_a").getValue().toString();
                //=====
                //spinner
                String sambung_kabel_jtr_jurusan_a=dataSnapshot.child("sambung_kabel_jtr_jurusan_a").getValue().toString();
//                =====
                //button jnyy lvtc nyfgby
                String jenis_kabel_jurusan_b=dataSnapshot.child("jenis_kabel_jurusan_b").getValue().toString();
                //=====
                //button 25 35 70 95 150
                String penampang_jurusan_b=dataSnapshot.child("penampang_jurusan_b").getValue().toString();
                //=====
                //spinner
                String sambung_kabel_jtr_jurusan_b=dataSnapshot.child("sambung_kabel_jtr_jurusan_b").getValue().toString();
                //=====
                //button jnyy lvtc nyfgby
                String jenis_kabel_jurusan_c=dataSnapshot.child("jenis_kabel_jurusan_c").getValue().toString();
                //=====
                //button 25 35 70 95 150
                String penampang_jurusan_c=dataSnapshot.child("penampang_jurusan_c").getValue().toString();
                //=====
                //spinner
                String sambung_kabel_jtr_jurusan_c=dataSnapshot.child("sambung_kabel_jtr_jurusan_c").getValue().toString();
                //=====
                //button jnyy lvtc nyfgby
                String jenis_kabel_jurusan_d=dataSnapshot.child("jenis_kabel_jurusan_d").getValue().toString();
                //=====
                //button 25 35 70 95 150
                String penampang_jurusan_d=dataSnapshot.child("penampang_jurusan_d").getValue().toString();
                //=====
                //spinner
                String sambung_kabel_jtr_jurusan_d=dataSnapshot.child("sambung_kabel_jtr_jurusan_d").getValue().toString();
                //=====
                //=======================

                TextView fv_namapetugas =(TextView)findViewById(R.id.fv_namapetugas);

                TextView fv_kapel =(TextView)findViewById(R.id.fv_kapel);
                TextView fv_kodegardu =(TextView)findViewById(R.id.fv_kodegardu);
                TextView fv_penyulang =(TextView)findViewById(R.id.fv_penyulang);
                TextView fv_daya =(TextView)findViewById(R.id.fv_daya);
                TextView fv_merk =(TextView)findViewById(R.id.fv_merk);
                TextView fv_noserie =(TextView)findViewById(R.id.fv_noserie);
                TextView fv_jumlahfasa =(TextView)findViewById(R.id.fv_jumlahfasa);
                TextView fv_tapoperasi =(TextView)findViewById(R.id.fv_tapoperasi);
                TextView fv_konstruksitrafo =(TextView)findViewById(R.id.fv_konstruksitrafo);
                TextView fv_rekondisi =(TextView)findViewById(R.id.fv_rekondisi);
                TextView fv_tahunpembuatan =(TextView)findViewById(R.id.fv_tahunpembuatan);
                TextView fv_kuncigardu =(TextView)findViewById(R.id.fv_kuncigardu);

                TextView fv_kokon =(TextView)findViewById(R.id.fv_kokon);
                TextView fv_schontrafo =(TextView)findViewById(R.id.fv_schontrafo);
                TextView fv_kebutuhanschoon =(TextView)findViewById(R.id.fv_kebutuhanschoon);
                TextView fv_primerphasaR =(TextView)findViewById(R.id.fv_primerphasaR);
                TextView fv_primerphasaS =(TextView)findViewById(R.id.fv_primerphasaS);
                TextView fv_primerphasaT =(TextView)findViewById(R.id.fv_primerphasaT);
                TextView fv_saklarutamaPHB =(TextView)findViewById(R.id.fv_saklarutamaPHB);
                TextView fv_PHBmerk =(TextView)findViewById(R.id.fv_PHBmerk);
                TextView fv_PHBArus =(TextView)findViewById(R.id.fv_PHBArus);

                final TextView jurusan_A_fuse_R =(TextView)findViewById(R.id.jurusan_A_fuse_R);
                final TextView jurusan_A_fuse_S =(TextView)findViewById(R.id.jurusan_A_fuse_S);
                final TextView jurusan_A_fuse_T =(TextView)findViewById(R.id.jurusan_A_fuse_T);

                TextView jurusan_B_fuse_R =(TextView)findViewById(R.id.jurusan_B_fuse_R);
                TextView jurusan_B_fuse_S =(TextView)findViewById(R.id.jurusan_B_fuse_S);
                TextView jurusan_B_fuse_T =(TextView)findViewById(R.id.jurusan_B_fuse_T);

                TextView jurusan_C_fuse_R =(TextView)findViewById(R.id.jurusan_C_fuse_R);
                TextView jurusan_C_fuse_S =(TextView)findViewById(R.id.jurusan_C_fuse_S);
                TextView jurusan_C_fuse_T =(TextView)findViewById(R.id.jurusan_C_fuse_T);

                TextView jurusan_D_fuse_R =(TextView)findViewById(R.id.jurusan_D_fuse_R);
                TextView jurusan_D_fuse_S =(TextView)findViewById(R.id.jurusan_D_fuse_S);
                TextView jurusan_D_fuse_T =(TextView)findViewById(R.id.jurusan_D_fuse_T);

//                TextView fv_fusejurusan =(TextView)findViewById(R.id.fv_NH_jurusan);

                final Button button_jurusan_A = (Button) findViewById(R.id.fuse_jurusan_A);
                final Button button_jurusan_B = (Button) findViewById(R.id.fuse_jurusan_B);
                final Button button_jurusan_C = (Button) findViewById(R.id.fuse_jurusan_C);
                final Button button_jurusan_D = (Button) findViewById(R.id.fuse_jurusan_D);

                final Button button_NH_jurusan_A = (Button) findViewById(R.id.NH_jurusan_A);
                final Button button_NH_jurusan_B = (Button) findViewById(R.id.NH_jurusan_B);
                final Button button_NH_jurusan_C = (Button) findViewById(R.id.NH_jurusan_C);
                final Button button_NH_jurusan_D = (Button) findViewById(R.id.NH_jurusan_D);


                final LinearLayout Jur_A_fuse = (LinearLayout) findViewById(R.id.text_gone_fuse_jurusan_A);
                final LinearLayout Jur_B_fuse = (LinearLayout) findViewById(R.id.text_gone_fuse_jurusan_B);
                final LinearLayout Jur_C_fuse = (LinearLayout) findViewById(R.id.text_gone_fuse_jurusan_C);
                final LinearLayout Jur_D_fuse = (LinearLayout) findViewById(R.id.text_gone_fuse_jurusan_D);

                final LinearLayout Jur_NH_A = (LinearLayout) findViewById(R.id.text_gone_NH_jurusan_A);
                final LinearLayout Jur_NH_B = (LinearLayout) findViewById(R.id.text_gone_NH_jurusan_B);
                final LinearLayout Jur_NH_C = (LinearLayout) findViewById(R.id.text_gone_NH_jurusan_C);
                final LinearLayout Jur_NH_D = (LinearLayout) findViewById(R.id.text_gone_NH_jurusan_D);

                final LinearLayout Jur_A_Data_Kabel = (LinearLayout) findViewById(R.id.jur_A_data_kabel);
                final LinearLayout Jur_B_Data_Kabel = (LinearLayout) findViewById(R.id.jur_B_data_kabel);
                final LinearLayout Jur_C_Data_Kabel = (LinearLayout) findViewById(R.id.jur_C_data_kabel);
                final LinearLayout Jur_D_Data_Kabel = (LinearLayout) findViewById(R.id.jur_D_data_kabel);





                final TextView NH_R_A =(TextView)findViewById(R.id.tv_NH_R_A);
                final TextView NH_S_A =(TextView)findViewById(R.id.tv_NH_S_A);
                final TextView NH_T_A =(TextView)findViewById(R.id.tv_NH_T_A);

                final TextView NH_R_B =(TextView)findViewById(R.id.tv_NH_R_B);
                final TextView NH_S_B =(TextView)findViewById(R.id.tv_NH_S_B);
                final TextView NH_T_B =(TextView)findViewById(R.id.tv_NH_T_B);

                final TextView NH_R_C =(TextView)findViewById(R.id.tv_NH_R_C);
                final TextView NH_S_C =(TextView)findViewById(R.id.tv_NH_S_C);
                final TextView NH_T_C =(TextView)findViewById(R.id.tv_NH_T_C);

                final TextView NH_R_D =(TextView)findViewById(R.id.tv_NH_R_D);
                final TextView NH_S_D =(TextView)findViewById(R.id.tv_NH_S_D);
                final TextView NH_T_D =(TextView)findViewById(R.id.tv_NH_T_D);

                final Button data_kabel_jurusan_A = (Button) findViewById(R.id.data_kabel_jurusan_A);
                final Button data_kabel_jurusan_B = (Button) findViewById(R.id.data_kabel_jurusan_B);
                final Button data_kabel_jurusan_C = (Button) findViewById(R.id.data_kabel_jurusan_C);
                final Button data_kabel_jurusan_D = (Button) findViewById(R.id.data_kabel_jurusan_D);

                final TextView jenis_kabel_A =(TextView)findViewById(R.id.fv_jeniskabel_A);
                final TextView jenis_kabel_B =(TextView)findViewById(R.id.fv_jeniskabel_B);
                final TextView jenis_kabel_C =(TextView)findViewById(R.id.fv_jeniskabel_C);
                final TextView jenis_kabel_D =(TextView)findViewById(R.id.fv_jeniskabel_D);

                final TextView penampang_outlet_A =(TextView)findViewById(R.id.fv_penampangOUTLET_A);
                final TextView penampang_outlet_B =(TextView)findViewById(R.id.fv_penampangOUTLET_B);
                final TextView penampang_outlet_C =(TextView)findViewById(R.id.fv_penampangOUTLET_C);
                final TextView penampang_outlet_D =(TextView)findViewById(R.id.fv_penampangOUTLET_D);

                final TextView sambungan_kabel_jtr_A = (TextView)findViewById(R.id.fv_sambungankabelkejtr_A);
                final TextView sambungan_kabel_jtr_B = (TextView)findViewById(R.id.fv_sambungankabelkejtr_B);
                final TextView sambungan_kabel_jtr_C = (TextView)findViewById(R.id.fv_sambungankabelkejtr_C);
                final TextView sambungan_kabel_jtr_D = (TextView)findViewById(R.id.fv_sambungankabelkejtr_D);






                TextView fv_jumlaharresterterpasang =(TextView)findViewById(R.id.fv_jumlaharresterterpasang);
                TextView fv_tahananarrester =(TextView)findViewById(R.id.fv_tahananarrester);
                TextView fv_tahanannetral =(TextView)findViewById(R.id.fv_tahanannetral);
                TextView fv_tahananbodytrafo =(TextView)findViewById(R.id.fv_tahananbodytrafo);
                TextView fv_jeniskabelINLET =(TextView)findViewById(R.id.fv_jeniskabelINLET);
                TextView fv_penampangINLET =(TextView)findViewById(R.id.fv_penampangINLET);
//                TextView fv_jeniskabelOUTLET =(TextView)findViewById(R.id.fv_jeniskabelOUTLET);
                TextView fv_jeniskabel_A =(TextView)findViewById(R.id.fv_jeniskabel_A);
                TextView fv_penampangOUTLET_A =(TextView)findViewById(R.id.fv_penampangOUTLET_A);
                TextView fv_sambungankabelkejtr_A =(TextView)findViewById(R.id.fv_sambungankabelkejtr_A);
                TextView tv_keterangan = (TextView) findViewById(R.id.tv_keterangan);

                fv_namapetugas.setText(petugas);
                fv_kapel.setText(kapel);
                fv_kodegardu.setText(kode_gardu);
                fv_penyulang.setText(penyulang);
                fv_daya.setText(daya);
                fv_merk.setText(merk);
                fv_noserie.setText(noserie);
                fv_jumlahfasa.setText(jlh_fasa);
                fv_tapoperasi.setText(tap_operasi);
                fv_konstruksitrafo.setText(kons_trafo);
                fv_rekondisi.setText(rekondisi);
                fv_tahunpembuatan.setText(tahun_pembuatan);
                fv_kuncigardu.setText(kunci_gardu);
                fv_kokon.setText(kokon);
                fv_schontrafo.setText(schoon_trafo);
                fv_kebutuhanschoon.setText(kebutuhan_schoon_PHB);
                fv_primerphasaR.setText(primer_phasa_r);
                fv_primerphasaS.setText(primer_phasa_s);
                fv_primerphasaT.setText(primer_phasa_t);
                if(saklar_utama_phb.toLowerCase().equals("tidak")){
                    fv_saklarutamaPHB.setText(saklar_utama_phb);
                    fv_PHBmerk.setText("-");
                    fv_PHBArus.setText("-");
                }else{
                    fv_saklarutamaPHB.setText(saklar_utama_phb);
                    fv_PHBmerk.setText(saklar_merk);
                    fv_PHBArus.setText(saklar_arus);
                }

                button_jurusan_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_A_fuse.setVisibility(View.VISIBLE);
                        Jur_C_fuse.setVisibility(View.INVISIBLE);
                        Jur_B_fuse.setVisibility(View.INVISIBLE);
                        Jur_D_fuse.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_jurusan_B.setSelected(false);
                        button_jurusan_C.setSelected(false);
                        button_jurusan_D.setSelected(false);
                    }
                });

                button_jurusan_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_B_fuse.setVisibility(View.VISIBLE);
                        Jur_A_fuse.setVisibility(View.INVISIBLE);
                        Jur_C_fuse.setVisibility(View.INVISIBLE);
                        Jur_D_fuse.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_jurusan_A.setSelected(false);
                        button_jurusan_C.setSelected(false);
                        button_jurusan_D.setSelected(false);
                    }
                });

                button_jurusan_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_C_fuse.setVisibility(View.VISIBLE);
                        Jur_A_fuse.setVisibility(View.INVISIBLE);
                        Jur_B_fuse.setVisibility(View.INVISIBLE);
                        Jur_D_fuse.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_jurusan_B.setSelected(false);
                        button_jurusan_A.setSelected(false);
                        button_jurusan_D.setSelected(false);
                    }
                });

                button_jurusan_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_D_fuse.setVisibility(View.VISIBLE);
                        Jur_A_fuse.setVisibility(View.INVISIBLE);
                        Jur_B_fuse.setVisibility(View.INVISIBLE);
                        Jur_C_fuse.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_jurusan_B.setSelected(false);
                        button_jurusan_A.setSelected(false);
                        button_jurusan_C.setSelected(false);
                    }
                });


                if(fuse_jurusan.toLowerCase().equals("jurusan a")){
                    jurusan_A_fuse_R.setText(fuse_R_Jur_A);
                    jurusan_A_fuse_S.setText(fuse_S_Jur_A);
                    jurusan_A_fuse_T.setText(fuse_T_Jur_A);
                    button_jurusan_A.setSelected(true);
                    Jur_A_fuse.setVisibility(View.VISIBLE);

                }else if(fuse_jurusan.toLowerCase().equals("jurusan b")){
                    jurusan_B_fuse_R.setText(fuse_R_Jur_B);
                    jurusan_B_fuse_S.setText(fuse_S_Jur_B);
                    jurusan_B_fuse_T.setText(fuse_T_Jur_B);
                    button_jurusan_B.setSelected(true);
                    Jur_B_fuse.setVisibility(View.VISIBLE);

                }else if(fuse_jurusan.toLowerCase().equals("jurusan c")){
                    jurusan_C_fuse_R.setText(fuse_R_Jur_C);
                    jurusan_C_fuse_S.setText(fuse_S_Jur_C);
                    jurusan_C_fuse_T.setText(fuse_T_Jur_C);
                    button_jurusan_C.setSelected(true);
                    Jur_C_fuse.setVisibility(View.VISIBLE);

                }
//                else if (fuse_jurusan.toLowerCase().equals("jurusan d")) {
//                    jurusan_D_fuse_R.setText(fuse_R_Jur_D);
//                    jurusan_D_fuse_S.setText(fuse_S_Jur_D);
//                    jurusan_D_fuse_T.setText(fuse_T_Jur_D);
//                    button_jurusan_D.setSelected(true);
//
//                }

                button_NH_jurusan_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_NH_A.setVisibility(View.VISIBLE);
                        Jur_NH_B.setVisibility(View.INVISIBLE);
                        Jur_NH_C.setVisibility(View.INVISIBLE);
                        Jur_NH_D.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_NH_jurusan_B.setSelected(false);
                        button_NH_jurusan_C.setSelected(false);
                        button_NH_jurusan_D.setSelected(false);
                    }
                });

                button_NH_jurusan_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_NH_B.setVisibility(View.VISIBLE);
                        Jur_NH_A.setVisibility(View.INVISIBLE);
                        Jur_NH_C.setVisibility(View.INVISIBLE);
                        Jur_NH_D.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_NH_jurusan_A.setSelected(false);
                        button_NH_jurusan_C.setSelected(false);
                        button_NH_jurusan_D.setSelected(false);
                    }
                });

                button_NH_jurusan_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_NH_C.setVisibility(View.VISIBLE);
                        Jur_NH_A.setVisibility(View.INVISIBLE);
                        Jur_NH_B.setVisibility(View.INVISIBLE);
                        Jur_NH_D.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_NH_jurusan_B.setSelected(false);
                        button_NH_jurusan_A.setSelected(false);
                        button_NH_jurusan_D.setSelected(false);
                    }
                });

                button_NH_jurusan_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_NH_D.setVisibility(View.VISIBLE);
                        Jur_NH_B.setVisibility(View.INVISIBLE);
                        Jur_NH_C.setVisibility(View.INVISIBLE);
                        Jur_NH_A.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        button_NH_jurusan_B.setSelected(false);
                        button_NH_jurusan_C.setSelected(false);
                        button_NH_jurusan_A.setSelected(false);
                    }
                });

                if(nh_jurusan.toLowerCase().equals("jurusan a")){

                    NH_R_A.setText(NH_R_Jur_A);
                    NH_S_A.setText(NH_S_Jur_A);
                    NH_T_A.setText(NH_T_Jur_A);
                    button_NH_jurusan_A.setSelected(true);
                    Jur_NH_A.setVisibility(View.VISIBLE);

                }else  if(nh_jurusan.toLowerCase().equals("jurusan b")){

                    NH_R_B.setText(NH_R_Jur_B);
                    NH_S_B.setText(NH_S_Jur_B);
                    NH_T_B.setText(NH_T_Jur_B);
                    button_NH_jurusan_B.setSelected(true);
                    Jur_NH_B.setVisibility(View.VISIBLE);

                }else  if(nh_jurusan.toLowerCase().equals("jurusan c")){
                    NH_R_C.setText(NH_R_Jur_C);
                    NH_S_C.setText(NH_S_Jur_C);
                    NH_T_C.setText(NH_T_Jur_C);
                    button_NH_jurusan_C.setSelected(true);
                    Jur_NH_C.setVisibility(View.VISIBLE);

                }else  if(nh_jurusan.toLowerCase().equals("jurusan d")){
                    NH_R_D.setText(NH_R_Jur_D);
                    NH_S_D.setText(NH_S_Jur_D);
                    NH_T_D.setText(NH_T_Jur_D);
                    button_NH_jurusan_D.setSelected(true);
                    Jur_NH_D.setVisibility(View.VISIBLE);

                }

                data_kabel_jurusan_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_A_Data_Kabel.setVisibility(View.VISIBLE);
                        Jur_B_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_C_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_D_Data_Kabel.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        data_kabel_jurusan_B.setSelected(false);
                        data_kabel_jurusan_C.setSelected(false);
                        data_kabel_jurusan_D.setSelected(false);
                    }
                });

                data_kabel_jurusan_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_A_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_B_Data_Kabel.setVisibility(View.VISIBLE);
                        Jur_C_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_D_Data_Kabel.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        data_kabel_jurusan_A.setSelected(false);
                        data_kabel_jurusan_C.setSelected(false);
                        data_kabel_jurusan_D.setSelected(false);
                    }
                });

                data_kabel_jurusan_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_A_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_B_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_C_Data_Kabel.setVisibility(View.VISIBLE);
                        Jur_D_Data_Kabel.setVisibility(View.INVISIBLE);
                        view.setSelected(true);
                        data_kabel_jurusan_A.setSelected(false);
                        data_kabel_jurusan_B.setSelected(false);
                        data_kabel_jurusan_D.setSelected(false);
                    }
                });

                data_kabel_jurusan_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Jur_A_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_B_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_C_Data_Kabel.setVisibility(View.INVISIBLE);
                        Jur_D_Data_Kabel.setVisibility(View.VISIBLE);
                        view.setSelected(true);
                        data_kabel_jurusan_A.setSelected(false);
                        data_kabel_jurusan_C.setSelected(false);
                        data_kabel_jurusan_B.setSelected(false);
                    }
                });


                fv_jumlaharresterterpasang.setText(arrester);
                fv_tahananarrester.setText(saklar_tahanan_arrester);
                fv_tahanannetral.setText(saklar_tahanan_netral);
                fv_tahananbodytrafo.setText(body_trafo);
                fv_jeniskabelINLET.setText(kabel_inlet);
                fv_penampangINLET.setText(penampang_inlet);

                if(data_kabel_jurusan.toLowerCase().equals("jurusan a")){
//                    fv_jeniskabelOUTLET.setText(data_kabel_jurusan);
                    fv_jeniskabel_A.setText(jenis_kabel_jurusan_a);
                    fv_penampangOUTLET_A.setText(penampang_jurusan_a);
                    fv_sambungankabelkejtr_A.setText(sambung_kabel_jtr_jurusan_a);
                }else if(data_kabel_jurusan.toLowerCase().equals("jurusan b")){
//                    fv_jeniskabelOUTLET.setText(data_kabel_jurusan);
                    fv_jeniskabel_A.setText(jenis_kabel_jurusan_b);
                    fv_penampangOUTLET_A.setText(penampang_jurusan_b);
                    fv_sambungankabelkejtr_A.setText(sambung_kabel_jtr_jurusan_b);
                }else if(data_kabel_jurusan.toLowerCase().equals("jurusan c")){
//                    fv_jeniskabelOUTLET.setText(data_kabel_jurusan);
                    fv_jeniskabel_A.setText(jenis_kabel_jurusan_c);
                    fv_penampangOUTLET_A.setText(penampang_jurusan_c);
                    fv_sambungankabelkejtr_A.setText(sambung_kabel_jtr_jurusan_c);
                }else if(data_kabel_jurusan.toLowerCase().equals("jurusan d")){
//                    fv_jeniskabelOUTLET.setText(data_kabel_jurusan);
                    fv_jeniskabel_A.setText(jenis_kabel_jurusan_d);
                    fv_penampangOUTLET_A.setText(penampang_jurusan_d);
                    fv_sambungankabelkejtr_A.setText(sambung_kabel_jtr_jurusan_d);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        TextView fv_penyulang =(TextView)findViewById(R.id.fv_penyulang);
        Intent myIntent = new Intent(getApplicationContext(), List_Pengaduan_Detail.class);
        myIntent.putExtra("penyulang",fv_penyulang.getText());
        startActivityForResult(myIntent, 0);
        return true;
    }
}
