package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

public enum LoaiHocPhan {
    HOC_PHAN_DAI_CUONG("Học phần đại cương"),
    HOC_PHAN_CO_SO_NGANH("Học phần cơ sở ngành"),
    HOC_PHAN_BAT_BUOC_NGANH("Học phần bắt buộc ngành"),
    HOC_PHAN_LUA_CHON_NGANH("Học phần lựa chọn ngành"),
    HOC_PHAN_TU_DO("Học phần tự do"),
    KLTN_CDTN_THUC_TAP("KLTN - CĐTN - Thực tập");

    private final String displayName;

    LoaiHocPhan(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}	