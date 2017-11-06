package com.digitalcreative.pengaduan_admin.model;

/**
 * Created by malianzikri on 9/20/17.
 */

public class Model_Pengaduan {
    String nama;
    String tanggal;
    String kode_gardu;
    String daya;
    String alamat;
    String uid;
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKode_gardu() {
        return kode_gardu;
    }

    public void setKode_gardu(String kode_gardu) {
        this.kode_gardu = kode_gardu;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


}
