package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_LoaiHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;

import java.util.List;
import java.util.Optional;

public interface ChuongTrinhHoc_LoaiHocPhanRepository extends JpaRepository<ChuongTrinhHoc_LoaiHocPhan, Long> {

    // ✅ Tìm tất cả loại học phần theo ID chương trình học
    List<ChuongTrinhHoc_LoaiHocPhan> findByChuongTrinhHocId(Long chuongTrinhHocId);

    // ✅ Xóa toàn bộ loại học phần theo ID chương trình học
    void deleteByChuongTrinhHocId(Long chuongTrinhHocId);

    // ✅ Kiểm tra loại học phần đã tồn tại trong chương trình học chưa
    boolean existsByChuongTrinhHocIdAndLoaiHocPhan(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan);

    // ✅ Lấy loại học phần cụ thể theo chương trình và loại
    Optional<ChuongTrinhHoc_LoaiHocPhan> findByChuongTrinhHocIdAndLoaiHocPhan(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan);
    
    Optional<ChuongTrinhHoc_LoaiHocPhan> findByIdAndChuongTrinhHocId(Long id, Long chuongTrinhHocId);
    

}
