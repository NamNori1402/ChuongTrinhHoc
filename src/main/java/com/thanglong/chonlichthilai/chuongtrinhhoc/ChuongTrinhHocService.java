package com.thanglong.chonlichthilai.chuongtrinhhoc;

import java.util.List;

public interface ChuongTrinhHocService {
    ChuongTrinhHoc save(ChuongTrinhHoc e);
    List<ChuongTrinhHoc> findAll();
    ChuongTrinhHoc findById(Long id);
    List<ChuongTrinhHoc> findByKhoa(String khoaHoc);
    
    void deleteById(Long id);
    void addMonHocToChuongTrinh(Long chuongTrinhHocId, MonHoc monHoc);
    void removeMonHocFromChuongTrinh(Long chuongTrinhHocId, Long monHocId); // Giữ nguyên tên method từ tin nhắn trước
    
}