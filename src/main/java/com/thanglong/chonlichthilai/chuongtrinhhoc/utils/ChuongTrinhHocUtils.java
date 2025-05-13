package com.thanglong.chonlichthilai.chuongtrinhhoc.utils;

public class ChuongTrinhHocUtils {
    public static String extractKhoaFromNganh(String nganh) {
        if (nganh == null || nganh.length() < 4) return null;
        return nganh.substring(2); // ví dụ TT31 → 31
    }

    public static String extractTenChuyenNganhTuNganh(String nganh) {
        return MaChuyenNganhMapping.fromMa(nganh);
    }
}
