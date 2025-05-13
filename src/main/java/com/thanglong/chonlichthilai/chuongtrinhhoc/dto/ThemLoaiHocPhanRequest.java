package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;

import lombok.Data;

@Data
public class ThemLoaiHocPhanRequest {
    private LoaiHocPhan loaiHocPhan;
    private int soTinChiToiThieu;
    private String maBatDauDuocTinh;

    // getters và setters
    public LoaiHocPhan getLoaiHocPhan() {
        return loaiHocPhan;
    }

    public void setLoaiHocPhan(LoaiHocPhan loaiHocPhan) {
        this.loaiHocPhan = loaiHocPhan;
    }

    public int getSoTinChiToiThieu() {
        return soTinChiToiThieu;
    }

    public void setSoTinChiToiThieu(int soTinChiToiThieu) {
        this.soTinChiToiThieu = soTinChiToiThieu;
    }

    public String getMaBatDauDuocTinh() {
        return maBatDauDuocTinh;
    }

    public void setMaBatDauDuocTinh(String maBatDauDuocTinh) {
        this.maBatDauDuocTinh = maBatDauDuocTinh;
    }
}