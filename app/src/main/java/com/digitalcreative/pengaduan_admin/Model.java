package com.digitalcreative.pengaduan_admin;

/**
 * Created by digitalcreative on 8/14/17.
 */

public class Model {
    String judul;
    String detail;

    public Model(String judul, String detail) {
        this.judul = judul;
        this.detail = detail;
    }

    public Model() {
    }

    public String getJudul() {
        return judul;
    }

    public String getDetail() {
        return detail;
    }
}
