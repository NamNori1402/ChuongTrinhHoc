package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import java.util.List;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.CapNhatLoaiHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ChuongTrinhHocResponse;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhan;

public interface ChuongTrinhHocService {
    // Các phương thức CRUD cơ bản
//    ChuongTrinhHoc save(ChuongTrinhHoc e);
    List<ChuongTrinhHoc> findAll();
    ChuongTrinhHoc findById(Long id);
    void deleteById(Long id);    
    
    ChuongTrinhHocResponse saveFullChuongTrinhHoc(ChuongTrinhHocRequest request);

    // Tìm kiếm theo khoa	
    List<ChuongTrinhHoc> findByKhoa(String khoaHoc);
    
    // Tìm kiếm chương trình học
    List<ChuongTrinhHoc> searchByKeyword(String keyword);
    List<ChuongTrinhHoc> findByMaHocPhan(String maHocPhan);
    List<ChuongTrinhHoc> findByTenHocPhan(String tenHocPhan);
    
    
    //void updateSoTinChiToiThieu(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan, int soTinChi);
    void capNhatLoaiHocPhan(Long chuongTrinhHocId,Long loaiHocPhanId, CapNhatLoaiHocPhanRequest request);


    // Quản lý học phần trong chương trình học
    void addHocPhanToChuongTrinh(Long chuongTrinhHocId, Long hocPhanId, LoaiHocPhan loaiHocPhan);
    void removeHocPhanFromChuongTrinh(Long chuongTrinhHocId, Long hocPhanId);
    List<HocPhan> getHocPhanByChuongTrinh(Long chuongTrinhHocId);
    List<HocPhan> searchHocPhanNotInChuongTrinh(Long chuongTrinhHocId, String keyword);
    List<String> addHocPhanToCTLHP(Long ctlhpId, List<Long> hocPhanIds);

    void themLoaiHocPhanChoChuongTrinh(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan, int soTinChiToiThieu, String maBatDauDuocTinh);
    List<ChuongTrinhHoc_LoaiHocPhan> getLoaiHocPhanByChuongTrinh(Long chuongTrinhHocId);
    
}