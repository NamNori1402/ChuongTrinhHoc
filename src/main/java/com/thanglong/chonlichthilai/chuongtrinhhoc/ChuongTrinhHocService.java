package com.thanglong.chonlichthilai.chuongtrinhhoc;

import java.util.List;

public interface ChuongTrinhHocService {
    ChuongTrinhHoc save(ChuongTrinhHoc e);
    List<ChuongTrinhHoc> findAll();
    ChuongTrinhHoc findById(Long id);
    List<ChuongTrinhHoc> findByKhoa(String khoaHoc);

    List<ChuongTrinhHoc> findByMonHoc(String maHocPhan, String tenMonHoc);
    List<ChuongTrinhHoc> searchByKeyword(String keyword);
    List<ChuongTrinhHoc> findByMaHocPhan(String maHocPhan);
    List<ChuongTrinhHoc> findByTenMonHoc(String tenMonHoc);
    void deleteById(Long id);
    void addMonHocToChuongTrinh(Long chuongTrinhHocId, MonHoc monHoc);
    void removeMonHocFromChuongTrinh(Long chuongTrinhHocId, Long monHocId); // Giữ nguyên tên method từ tin nhắn trước
    
}