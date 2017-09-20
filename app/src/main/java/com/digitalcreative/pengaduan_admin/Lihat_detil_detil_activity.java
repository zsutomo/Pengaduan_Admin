package com.digitalcreative.pengaduan_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
        getSupportActionBar().setTitle("Form Pengaduan");
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
                String primer_phasa_r=dataSnapshot.child("primer_phasa_r").getValue().toString() ;
                String primer_phasa_s=dataSnapshot.child("primer_phasa_s").getValue().toString() ;
                String primer_phasa_t=dataSnapshot.child("primer_phasa_t").getValue().toString() ;
                String saklar_merk=dataSnapshot.child("saklar_merk").getValue().toString() ;
                String saklar_arus=dataSnapshot.child("saklar_arus").getValue().toString() ;
                String fuse_R_Jur_A=dataSnapshot.child("fuse_R_Jur_A").getValue().toString() ;
                String fuse_S_Jur_A=dataSnapshot.child("fuse_S_Jur_A").getValue().toString() ;
                String fuse_T_Jur_A=dataSnapshot.child("fuse_T_Jur_A").getValue().toString() ;
                String fuse_R_Jur_B=dataSnapshot.child("fuse_R_Jur_B").getValue().toString() ;
                String fuse_S_Jur_B=dataSnapshot.child("fuse_S_Jur_B").getValue().toString() ;
                String fuse_T_Jur_B=dataSnapshot.child("fuse_T_Jur_B").getValue().toString() ;
                String fuse_R_Jur_C=dataSnapshot.child("fuse_R_Jur_C").getValue().toString() ;
                String fuse_S_Jur_C=dataSnapshot.child("fuse_S_Jur_C").getValue().toString() ;
                String fuse_T_Jur_C=dataSnapshot.child("fuse_T_Jur_C").getValue().toString() ;
                String NH_R_Jur_A=dataSnapshot.child("nh_R_Jur_A").getValue().toString() ;
                String NH_S_Jur_A =dataSnapshot.child("nh_S_Jur_A").getValue().toString();
                String NH_T_Jur_A=dataSnapshot.child("nh_T_Jur_A").getValue().toString() ;
                String NH_R_Jur_B=dataSnapshot.child("nh_R_Jur_B").getValue().toString() ;
                String NH_S_Jur_B=dataSnapshot.child("nh_S_Jur_B").getValue().toString() ;
                String NH_T_Jur_B=dataSnapshot.child("nh_T_Jur_B").getValue().toString() ;
                String NH_R_Jur_C=dataSnapshot.child("nh_R_Jur_C").getValue().toString() ;
                String NH_S_Jur_C=dataSnapshot.child("nh_S_Jur_C").getValue().toString() ;
                String NH_T_Jur_C=dataSnapshot.child("nh_T_Jur_C").getValue().toString() ;
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
                //button jnyy lvtc nyfgby
                String jenis_kabel_jurusan_a=dataSnapshot.child("jenis_kabel_jurusan_a").getValue().toString();
                //=====
                //button 25 35 70 95 150
                String penampang_jurusan_a=dataSnapshot.child("penampang_jurusan_a").getValue().toString();
                //=====
                //spinner
                String sambung_kabel_jtr_jurusan_a=dataSnapshot.child("sambung_kabel_jtr_jurusan_a").getValue().toString();
                //=====
//                //button jnyy lvtc nyfgby
//                String jenis_kabel_jurusan_b=dataSnapshot.child("jenis_kabel_jurusan_b").getValue().toString();
//                //=====
//                //button 25 35 70 95 150
//                String penampang_jurusan_b=dataSnapshot.child("penampang_jurusan_b").getValue().toString();
//                //=====
//                //spinner
//                String sambung_kabel_jtr_jurusan_b=dataSnapshot.child("sambung_kabel_jtr_jurusan_b").getValue().toString();
//                //=====
//                //button jnyy lvtc nyfgby
//                String jenis_kabel_jurusan_c=dataSnapshot.child("jenis_kabel_jurusan_c").getValue().toString();
//                //=====
//                //button 25 35 70 95 150
//                String penampang_jurusan_c=dataSnapshot.child("penampang_jurusan_c").getValue().toString();
//                //=====
//                //spinner
//                String sambung_kabel_jtr_jurusan_c=dataSnapshot.child("sambung_kabel_jtr_jurusan_c").getValue().toString();
//                //=====
//                //button jnyy lvtc nyfgby
//                String jenis_kabel_jurusan_d=dataSnapshot.child("jenis_kabel_jurusan_d").getValue().toString();
//                //=====
//                //button 25 35 70 95 150
//                String penampang_jurusan_d=dataSnapshot.child("penampang_jurusan_d").getValue().toString();
//                //=====
//                //spinner
//                String sambung_kabel_jtr_jurusan_d=dataSnapshot.child("sambung_kabel_jtr_jurusan_d").getValue().toString();
//                //=====
//                //=======================

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
                TextView fv_PHBmerk =(TextView)findViewById(R.id.fv_PHBmerk);
                TextView fv_PHBArus =(TextView)findViewById(R.id.fv_PHBArus);
                TextView fv_fuse_R =(TextView)findViewById(R.id.fv_fuse_R);
                TextView fuse_S =(TextView)findViewById(R.id.fuse_S);
                TextView fv_fuse_T =(TextView)findViewById(R.id.fv_fuse_T);
                TextView fv_NH_R =(TextView)findViewById(R.id.fv_NH_R);
                TextView NH_S =(TextView)findViewById(R.id.NH_S);
                TextView NH_T =(TextView)findViewById(R.id.fv_NH_T);
                TextView fv_jumlaharresterterpasang =(TextView)findViewById(R.id.fv_jumlaharresterterpasang);
                TextView fv_tahananarrester =(TextView)findViewById(R.id.fv_tahananarrester);
                TextView fv_tahanannetral =(TextView)findViewById(R.id.fv_tahanannetral);
                TextView fv_tahananbodytrafo =(TextView)findViewById(R.id.fv_tahananbodytrafo);
                TextView fv_jeniskabelINLET =(TextView)findViewById(R.id.fv_jeniskabelINLET);
                TextView fv_penampangINLET =(TextView)findViewById(R.id.fv_penampangINLET);
                TextView fv_jeniskabel =(TextView)findViewById(R.id.fv_jeniskabel);
                TextView fv_penampangOUTLET =(TextView)findViewById(R.id.fv_penampangOUTLET);
                TextView fv_sambungankabelkejtr =(TextView)findViewById(R.id.fv_sambungankabelkejtr);

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
                fv_PHBmerk.setText(saklar_merk);
                fv_PHBArus.setText(saklar_arus);
                fv_fuse_R.setText(fuse_R_Jur_A);
                fuse_S.setText(fuse_S_Jur_A);
                fv_fuse_T.setText(fuse_T_Jur_A);
                fv_NH_R.setText(NH_R_Jur_A);
                NH_S.setText(NH_S_Jur_A);
                NH_T.setText(NH_T_Jur_A);
                fv_jumlaharresterterpasang.setText(arrester);
                fv_tahananarrester.setText(saklar_tahanan_arrester);
                fv_tahanannetral.setText(saklar_tahanan_netral);
                fv_tahananbodytrafo.setText(body_trafo);
                fv_jeniskabelINLET.setText(kabel_inlet);
                fv_penampangINLET.setText(penampang_inlet);
                fv_jeniskabel.setText(jenis_kabel_jurusan_a);
                fv_penampangOUTLET.setText(penampang_jurusan_a);
                fv_sambungankabelkejtr.setText(sambung_kabel_jtr_jurusan_a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
