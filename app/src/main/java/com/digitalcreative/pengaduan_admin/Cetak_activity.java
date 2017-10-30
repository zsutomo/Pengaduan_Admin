package com.digitalcreative.pengaduan_admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter;
import com.digitalcreative.pengaduan_admin.adapter.pengaduanlistadapter_detail;
import com.digitalcreative.pengaduan_admin.model.Model_Pengaduan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Cetak_activity extends AppCompatActivity {
    Context context;
    String[] pengaduan = {"Pengaduan 1","Pengaduan 2","Pengaduan 3","Pengaduan 4","Pengaduan 5"};
    private ListView listView;
    private List<Model> list;
    private pengaduanlistadapter listadapter;
    private String judul;
    private String detail;
    private DatabaseReference mDatabase;

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
        listView = (ListView)findViewById(R.id.list_pengaduan2);
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
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Cetak_activity.this);
                        builder.setMessage("Apakah Anda Ingin Mencetak Form ini?");
                        builder.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, final int j) {
                                        final String penyulang=list.get(i).getJudul();
                                        Toast.makeText(Cetak_activity.this, "Berhasil di Print "+penyulang, Toast.LENGTH_SHORT).show();

                                        final boolean[] Success = {false};

                                        mDatabase.child("pengaduan").child(list.get(i).getJudul()).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                Success[0] = saveExcelFile(Cetak_activity.this.getApplicationContext(), penyulang+".xls", dataSnapshot);

                                                if(Success[0] == true){
                                                    Toast.makeText(Cetak_activity.this.getApplicationContext(), "Excel berhasil disimpan", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(Cetak_activity.this.getApplicationContext(), "Excel gagal disimpan", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean saveExcelFile(Context context, String fileName, DataSnapshot dataSnapshot) {
        int baris = 0;
        System.out.println(fileName);
        // check if available and not read only
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.w("FileUtils", "Storage not available or read only");
            return false;
        }

        boolean success = false;

        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

//        //Cell style for header row
//        CellStyle cs = wb.createCellStyle();
//        cs.setFillForegroundColor(HSSFColor.LIME.index);
//        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("Laporan Pengaduan");



        Row row = sheet1.createRow(0);

        c = row.createCell(0);
        c.setCellValue("petugas");
//        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("kapel");
//        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("kode_gardu");
//        c.setCellStyle(cs);

        c = row.createCell(3);
        c.setCellValue("penyulang");
//        c.setCellStyle(cs);

        c = row.createCell(4);
        c.setCellValue("daya");
//        c.setCellStyle(cs);

        c = row.createCell(5);
        c.setCellValue("merk");
        //       c.setCellStyle(cs);

        c = row.createCell(6);
        c.setCellValue("noserie");

        c = row.createCell(7);
        c.setCellValue("jlh_fasa");
//        c.setCellStyle(cs);

        c = row.createCell(8);
        c.setCellValue("tap_operasi");
//        c.setCellStyle(cs);

        c = row.createCell(9);
        c.setCellValue("kons_trafo");
        //       c.setCellStyle(cs);


        c = row.createCell(10);
        c.setCellValue("rekondisi");
//        c.setCellStyle(cs);

        c = row.createCell(11);
        c.setCellValue("tahun_pembuatan");
//        c.setCellStyle(cs);

        c = row.createCell(12);
        c.setCellValue("kunci_gardu");
//        c.setCellStyle(cs);

        c = row.createCell(13);
        c.setCellValue("kokon");
//        c.setCellStyle(cs);

        c = row.createCell(14);
        c.setCellValue("schoon_trafo");
//        c.setCellStyle(cs);

        c = row.createCell(15);
        c.setCellValue("kebutuhan_schoon_PHB");
        //       c.setCellStyle(cs);

        c = row.createCell(16);
        c.setCellValue("primer_phasa_r");

        c = row.createCell(17);
        c.setCellValue("primer_phasa_s");
//        c.setCellStyle(cs);

        c = row.createCell(18);
        c.setCellValue("primer_phasa_t");
//        c.setCellStyle(cs);

        c = row.createCell(19);
        c.setCellValue("saklar_utama_phb");
        //       c.setCellStyle(cs);

        c = row.createCell(20);
        c.setCellValue("saklar_merk");
//        c.setCellStyle(cs);

        c = row.createCell(21);
        c.setCellValue("saklar_arus");
//        c.setCellStyle(cs);

        c = row.createCell(22);
        c.setCellValue("fuse_jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(23);
        c.setCellValue("fuse_R_Jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(24);
        c.setCellValue("fuse_S_Jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(25);
        c.setCellValue("fuse_T_Jurusan");
        //       c.setCellStyle(cs);

        c = row.createCell(26);
        c.setCellValue("nh_jurusan");

        c = row.createCell(27);
        c.setCellValue("NH_R_Jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(28);
        c.setCellValue("NH_S_Jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(29);
        c.setCellValue("NH_T_Jurusan");
        //       c.setCellStyle(cs);

        c = row.createCell(30);
        c.setCellValue("arrester");
//        c.setCellStyle(cs);

        c = row.createCell(31);
        c.setCellValue("saklar_tahanan_arrester");
//        c.setCellStyle(cs);

        c = row.createCell(32);
        c.setCellValue("saklar_tahanan_netral");
//        c.setCellStyle(cs);

        c = row.createCell(33);
        c.setCellValue("body_trafo");
//        c.setCellStyle(cs);

        c = row.createCell(34);
        c.setCellValue("kabel_inlet");
//        c.setCellStyle(cs);

        c = row.createCell(35);
        c.setCellValue("penampang_inlet");
        //       c.setCellStyle(cs);

        c = row.createCell(36);
        c.setCellValue("data_kabel_jurusan");

        c = row.createCell(37);
        c.setCellValue("jenis_kabel_jurusan");
//        c.setCellStyle(cs);

        c = row.createCell(38);
        c.setCellValue("penampang kabel");
//        c.setCellStyle(cs);

        c = row.createCell(39);
        c.setCellValue("sambung_kabel_jtr");
        //       c.setCellStyle(cs);




        sheet1.setColumnWidth(0, (15 * 500));
        sheet1.setColumnWidth(1, (15 * 500));
        sheet1.setColumnWidth(2, (15 * 500));
        sheet1.setColumnWidth(3, (15 * 500));
        sheet1.setColumnWidth(4, (15 * 500));
        sheet1.setColumnWidth(5, (15 * 500));
        sheet1.setColumnWidth(6, (15 * 500));
        sheet1.setColumnWidth(7, (15 * 500));
        sheet1.setColumnWidth(8, (15 * 500));
        sheet1.setColumnWidth(9, (15 * 500));

        sheet1.setColumnWidth(10, (15 * 500));
        sheet1.setColumnWidth(11, (15 * 500));
        sheet1.setColumnWidth(12, (15 * 500));
        sheet1.setColumnWidth(13, (15 * 500));
        sheet1.setColumnWidth(14, (15 * 500));
        sheet1.setColumnWidth(15, (15 * 500));
        sheet1.setColumnWidth(16, (15 * 500));
        sheet1.setColumnWidth(17, (15 * 500));
        sheet1.setColumnWidth(18, (15 * 500));
        sheet1.setColumnWidth(19, (15 * 500));

        sheet1.setColumnWidth(20, (15 * 500));
        sheet1.setColumnWidth(21, (15 * 500));
        sheet1.setColumnWidth(22, (15 * 500));
        sheet1.setColumnWidth(23, (15 * 500));
        sheet1.setColumnWidth(24, (15 * 500));
        sheet1.setColumnWidth(25, (15 * 500));
        sheet1.setColumnWidth(26, (15 * 500));
        sheet1.setColumnWidth(27, (15 * 500));
        sheet1.setColumnWidth(28, (15 * 500));
        sheet1.setColumnWidth(29, (15 * 500));

        sheet1.setColumnWidth(30, (15 * 500));
        sheet1.setColumnWidth(31, (15 * 500));
        sheet1.setColumnWidth(32, (15 * 500));
        sheet1.setColumnWidth(33, (15 * 500));
        sheet1.setColumnWidth(34, (15 * 500));
        sheet1.setColumnWidth(35, (15 * 500));
        sheet1.setColumnWidth(36, (15 * 500));
        sheet1.setColumnWidth(37, (15 * 500));
        sheet1.setColumnWidth(38, (15 * 500));
        sheet1.setColumnWidth(39, (15 * 500));



        int count=0;
        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            // Generate column headings
            String tanggal=postSnapshot.child("tanggal").getValue().toString();
            String alamat=postSnapshot.child("alamat").getValue().toString();
            //first
            String petugas=postSnapshot.child("petugas").getValue().toString() ;
            String kapel=postSnapshot.child("kapel").getValue().toString() ;
            String kode_gardu=postSnapshot.child("kode_gardu").getValue().toString() ;
            //spinner
            String penyulang=postSnapshot.child("penyulang").getValue().toString();
            //=====
            String daya=postSnapshot.child("daya").getValue().toString() ;
            String merk=postSnapshot.child("merk").getValue().toString() ;
            String noserie=postSnapshot.child("noserie").getValue().toString() ;
            String jlh_fasa=postSnapshot.child("jlh_fasa").getValue().toString() ;
            String tap_operasi=postSnapshot.child("tap_operasi").getValue().toString() ;
            String kons_trafo=postSnapshot.child("kons_trafo").getValue().toString() ;
            //button ya tidak
            String rekondisi=postSnapshot.child("rekondisi").getValue().toString();
            //=====
            String tahun_pembuatan=postSnapshot.child("tahun_pembuatan").getValue().toString() ;
            //button ya tidak
            String kunci_gardu=postSnapshot.child("kunci_gardu").getValue().toString();
            //=====
            //button ya tidak
            String kokon=postSnapshot.child("kokon").getValue().toString();
            //=====
            //button angka 3 2 1 0
            String schoon_trafo=postSnapshot.child("schoon_trafo").getValue().toString();
            //=====
            String kebutuhan_schoon_PHB =postSnapshot.child("kebutuhan_schoon_PHB").getValue().toString();;
            //Data Pengaman Trafo
            String saklar_utama_phb=postSnapshot.child("saklar_utama_phb").getValue().toString() ;
            String primer_phasa_r=postSnapshot.child("primer_phasa_r").getValue().toString() ;
            String primer_phasa_s=postSnapshot.child("primer_phasa_s").getValue().toString() ;
            String primer_phasa_t=postSnapshot.child("primer_phasa_t").getValue().toString() ;

            String saklar_merk=postSnapshot.child("saklar_merk").getValue().toString() ;
            String saklar_arus=postSnapshot.child("saklar_arus").getValue().toString() ;

            String fuse_jurusan=postSnapshot.child("fuse_jurusan").getValue().toString() ;
            String fuse_R_Jur_A=postSnapshot.child("fuse_R_Jur_A").getValue().toString() ;
            String fuse_S_Jur_A=postSnapshot.child("fuse_S_Jur_A").getValue().toString() ;
            String fuse_T_Jur_A=postSnapshot.child("fuse_T_Jur_A").getValue().toString() ;
            String fuse_R_Jur_B=postSnapshot.child("fuse_R_Jur_B").getValue().toString() ;
            String fuse_S_Jur_B=postSnapshot.child("fuse_S_Jur_B").getValue().toString() ;
            String fuse_T_Jur_B=postSnapshot.child("fuse_T_Jur_B").getValue().toString() ;
            String fuse_R_Jur_C=postSnapshot.child("fuse_R_Jur_C").getValue().toString() ;
            String fuse_S_Jur_C=postSnapshot.child("fuse_S_Jur_C").getValue().toString() ;
            String fuse_T_Jur_C=postSnapshot.child("fuse_T_Jur_C").getValue().toString() ;

            String nh_jurusan=postSnapshot.child("nh_jurusan").getValue().toString() ;
            String NH_R_Jur_A=postSnapshot.child("nh_R_Jur_A").getValue().toString() ;
            String NH_S_Jur_A =postSnapshot.child("nh_S_Jur_A").getValue().toString();
            String NH_T_Jur_A=postSnapshot.child("nh_T_Jur_A").getValue().toString() ;
            String NH_R_Jur_B=postSnapshot.child("nh_R_Jur_B").getValue().toString() ;
            String NH_S_Jur_B=postSnapshot.child("nh_S_Jur_B").getValue().toString() ;
            String NH_T_Jur_B=postSnapshot.child("nh_T_Jur_B").getValue().toString() ;
            String NH_R_Jur_C=postSnapshot.child("nh_R_Jur_C").getValue().toString() ;
            String NH_S_Jur_C=postSnapshot.child("nh_S_Jur_C").getValue().toString() ;
            String NH_T_Jur_C=postSnapshot.child("nh_T_Jur_C").getValue().toString() ;
            String NH_R_Jur_D=postSnapshot.child("nh_R_Jur_D").getValue().toString() ;
            String NH_S_Jur_D=postSnapshot.child("nh_S_Jur_D").getValue().toString() ;
            String NH_T_Jur_D=postSnapshot.child("nh_T_Jur_D").getValue().toString() ;

            String arrester=postSnapshot.child("arrester").getValue().toString() ;
            //===============
            //Data Pertahanan
            String saklar_tahanan_arrester=postSnapshot.child("saklar_tahanan_arrester").getValue().toString();
            String saklar_tahanan_netral=postSnapshot.child("saklar_tahanan_netral").getValue().toString() ;
            String body_trafo=postSnapshot.child("body_trafo").getValue().toString() ;
            //=============
            //Data Kabel
            //button nyy lvtc nyfgby
            String kabel_inlet=postSnapshot.child("kabel_inlet").getValue().toString();
            //=====
            //button 70 95 150 240
            String penampang_inlet=postSnapshot.child("penampang_inlet").getValue().toString();
            //=====


            //Jenis Kabel Outlet
            String data_kabel_jurusan=postSnapshot.child("data_kabel_jurusan").getValue().toString();
            //button jnyy lvtc nyfgby
            String jenis_kabel_jurusan_a=postSnapshot.child("jenis_kabel_jurusan_a").getValue().toString();
            //=====
            //button 25 35 70 95 150
            String penampang_jurusan_a=postSnapshot.child("penampang_jurusan_a").getValue().toString();
            //=====
            //spinner
            String sambung_kabel_jtr_jurusan_a=postSnapshot.child("sambung_kabel_jtr_jurusan_a").getValue().toString();
//                =====
            //button jnyy lvtc nyfgby
            String jenis_kabel_jurusan_b=postSnapshot.child("jenis_kabel_jurusan_b").getValue().toString();
            //=====
            //button 25 35 70 95 150
            String penampang_jurusan_b=postSnapshot.child("penampang_jurusan_b").getValue().toString();
            //=====
            //spinner
            String sambung_kabel_jtr_jurusan_b=postSnapshot.child("sambung_kabel_jtr_jurusan_b").getValue().toString();
            //=====
            //button jnyy lvtc nyfgby
            String jenis_kabel_jurusan_c=postSnapshot.child("jenis_kabel_jurusan_c").getValue().toString();
            //=====
            //button 25 35 70 95 150
            String penampang_jurusan_c=postSnapshot.child("penampang_jurusan_c").getValue().toString();
            //=====
            //spinner
            String sambung_kabel_jtr_jurusan_c=postSnapshot.child("sambung_kabel_jtr_jurusan_c").getValue().toString();
            //=====
            //button jnyy lvtc nyfgby
            String jenis_kabel_jurusan_d=postSnapshot.child("jenis_kabel_jurusan_d").getValue().toString();
            //=====
            //button 25 35 70 95 150
            String penampang_jurusan_d=postSnapshot.child("penampang_jurusan_d").getValue().toString();
            //=====
            //spinner
            String sambung_kabel_jtr_jurusan_d=postSnapshot.child("sambung_kabel_jtr_jurusan_d").getValue().toString();
            //=====
            //=======================

            row = sheet1.createRow(count + 1);

            c = row.createCell(0);
            c.setCellValue(petugas);

            c = row.createCell(1);
            c.setCellValue(kapel);

            c = row.createCell(2);
            c.setCellValue(kode_gardu);

            c = row.createCell(3);
            c.setCellValue(penyulang);

            c = row.createCell(4);
            c.setCellValue(daya);

            c = row.createCell(5);
            c.setCellValue(merk);

            c = row.createCell(6);
            c.setCellValue(noserie);

            c = row.createCell(7);
            c.setCellValue(jlh_fasa);

            c = row.createCell(8);
            c.setCellValue(tap_operasi);

            c = row.createCell(9);
            c.setCellValue(kons_trafo);

            c = row.createCell(10);
            c.setCellValue(rekondisi);

            c = row.createCell(11);
            c.setCellValue(tahun_pembuatan);

            c = row.createCell(12);
            c.setCellValue(kunci_gardu);

            c = row.createCell(13);
            c.setCellValue(kokon);

            c = row.createCell(14);
            c.setCellValue(schoon_trafo);

            c = row.createCell(15);
            c.setCellValue(kebutuhan_schoon_PHB);

            c = row.createCell(16);
            c.setCellValue(primer_phasa_r);

            c = row.createCell(17);
            c.setCellValue(primer_phasa_s);

            c = row.createCell(18);
            c.setCellValue(primer_phasa_t);

            if(saklar_utama_phb.toLowerCase().equals("tidak")){
                c = row.createCell(19);
                c.setCellValue(saklar_utama_phb);

                c = row.createCell(20);
                c.setCellValue("-");

                c = row.createCell(21);
                c.setCellValue("-");
            }else{
                c = row.createCell(19);
                c.setCellValue(saklar_utama_phb);

                c = row.createCell(20);
                c.setCellValue(saklar_merk);

                c = row.createCell(21);
                c.setCellValue(saklar_arus);
            }

            if(fuse_jurusan.toLowerCase().equals("jurusan a")){
                c = row.createCell(22);
                c.setCellValue(fuse_jurusan);

                c = row.createCell(23);
                c.setCellValue(fuse_R_Jur_A);

                c = row.createCell(24);
                c.setCellValue(fuse_S_Jur_A);

                c = row.createCell(25);
                c.setCellValue(fuse_T_Jur_A);
            }else if(fuse_jurusan.toLowerCase().equals("jurusan b")){
                c = row.createCell(22);
                c.setCellValue(fuse_jurusan);

                c = row.createCell(23);
                c.setCellValue(fuse_R_Jur_B);

                c = row.createCell(24);
                c.setCellValue(fuse_S_Jur_B);

                c = row.createCell(25);
                c.setCellValue(fuse_T_Jur_B);
            }else if(fuse_jurusan.toLowerCase().equals("jurusan c")){
                c = row.createCell(22);
                c.setCellValue(fuse_jurusan);

                c = row.createCell(23);
                c.setCellValue(fuse_R_Jur_C);

                c = row.createCell(24);
                c.setCellValue(fuse_S_Jur_C);

                c = row.createCell(25);
                c.setCellValue(fuse_T_Jur_C);

            }

            if(nh_jurusan.toLowerCase().equals("jurusan a")){
                c = row.createCell(26);
                c.setCellValue(nh_jurusan);

                c = row.createCell(27);
                c.setCellValue(NH_R_Jur_A);

                c = row.createCell(28);
                c.setCellValue(NH_S_Jur_A);

                c = row.createCell(29);
                c.setCellValue(NH_T_Jur_A);

            }else  if(nh_jurusan.toLowerCase().equals("jurusan b")){
                c = row.createCell(26);
                c.setCellValue(nh_jurusan);

                c = row.createCell(27);
                c.setCellValue(NH_R_Jur_B);

                c = row.createCell(28);
                c.setCellValue(NH_S_Jur_B);

                c = row.createCell(29);
                c.setCellValue(NH_T_Jur_B);

            }else  if(nh_jurusan.toLowerCase().equals("jurusan c")){
                c = row.createCell(26);
                c.setCellValue(nh_jurusan);

                c = row.createCell(27);
                c.setCellValue(NH_R_Jur_C);

                c = row.createCell(28);
                c.setCellValue(NH_S_Jur_C);

                c = row.createCell(29);
                c.setCellValue(NH_T_Jur_C);

            }else  if(nh_jurusan.toLowerCase().equals("jurusan d")){
                c = row.createCell(26);
                c.setCellValue(nh_jurusan);

                c = row.createCell(27);
                c.setCellValue(NH_R_Jur_D);

                c = row.createCell(28);
                c.setCellValue(NH_S_Jur_D);

                c = row.createCell(29);
                c.setCellValue(NH_T_Jur_D);

            }


            c = row.createCell(30);
            c.setCellValue(arrester);

            c = row.createCell(31);
            c.setCellValue(saklar_tahanan_arrester);

            c = row.createCell(32);
            c.setCellValue(saklar_tahanan_netral);

            c = row.createCell(33);
            c.setCellValue(body_trafo);

            c = row.createCell(34);
            c.setCellValue(kabel_inlet);

            c = row.createCell(35);
            c.setCellValue(penampang_inlet);

            if(data_kabel_jurusan.toLowerCase().equals("jurusan a")){
                c = row.createCell(36);
                c.setCellValue(data_kabel_jurusan);

                c = row.createCell(37);
                c.setCellValue(jenis_kabel_jurusan_a);

                c = row.createCell(38);
                c.setCellValue(penampang_jurusan_a);

                c = row.createCell(39);
                c.setCellValue(sambung_kabel_jtr_jurusan_a);
            }else if(data_kabel_jurusan.toLowerCase().equals("jurusan b")){
                c = row.createCell(36);
                c.setCellValue(data_kabel_jurusan);

                c = row.createCell(37);
                c.setCellValue(jenis_kabel_jurusan_b);

                c = row.createCell(38);
                c.setCellValue(penampang_jurusan_b);

                c = row.createCell(39);
                c.setCellValue(sambung_kabel_jtr_jurusan_b);
            }else if(data_kabel_jurusan.toLowerCase().equals("jurusan c")){
                c = row.createCell(36);
                c.setCellValue(data_kabel_jurusan);

                c = row.createCell(37);
                c.setCellValue(jenis_kabel_jurusan_c);

                c = row.createCell(38);
                c.setCellValue(penampang_jurusan_c);

                c = row.createCell(39);
                c.setCellValue(sambung_kabel_jtr_jurusan_c);
            }else if(data_kabel_jurusan.toLowerCase().equals("jurusan d")){
                c = row.createCell(36);
                c.setCellValue(data_kabel_jurusan);

                c = row.createCell(37);
                c.setCellValue(jenis_kabel_jurusan_d);

                c = row.createCell(38);
                c.setCellValue(penampang_jurusan_d);

                c = row.createCell(39);
                c.setCellValue(sambung_kabel_jtr_jurusan_d);
            }
            count=count+1;
        }

        // Create a path where we will place our List of objects on external storage
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream os = null;

        try {
            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);
            success = true;
        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception ex) {
            }
        }

        return success;
    }
}
