package com.thanglong.chonlichthilai.chuongtrinhhoc.utils;

public enum MaChuyenNganhMapping {
    TT("CONG_NGHE_THONG_TIN"),
    TA("TRI_TUE_NHAN_TAO"),
    TI("KHOA_HOC_MAY_TINH"),
    TE("HE_THONG_THONG_TIN"),
    TC("MANG_MAY_TINH"),
    // ... thêm mã và tên chuyên ngành tương ứng nếu có

    UNKNOWN("UNKNOWN");

    private final String tenChuyenNganh;

    MaChuyenNganhMapping(String tenChuyenNganh) {
        this.tenChuyenNganh = tenChuyenNganh;
    }

    public String getTenChuyenNganh() {
        return tenChuyenNganh;
    }

    public static String fromMa(String maNganh) {
        if (maNganh == null || maNganh.length() < 2) return UNKNOWN.tenChuyenNganh;
        String ma = maNganh.substring(0, 2).toUpperCase();
        for (MaChuyenNganhMapping mapping : values()) {
            if (mapping.name().equals(ma)) {
                return mapping.getTenChuyenNganh();
            }
        }
        return UNKNOWN.tenChuyenNganh;
    }
}
