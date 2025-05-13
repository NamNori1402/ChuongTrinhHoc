package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc_HocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;
import java.util.List;

public interface ChuongTrinhHoc_HocPhanRepository extends JpaRepository<ChuongTrinhHoc_HocPhan, Long> {

    // ✅ Lấy danh sách học phần theo chương trình học
    List<ChuongTrinhHoc_HocPhan> findByChuongTrinhHocId(Long chuongTrinhHocId);

    // ✅ Kiểm tra học phần đã tồn tại trong chương trình chưa
    boolean existsByChuongTrinhHocIdAndHocPhanId(Long chuongTrinhHocId, Long hocPhanId);

    // ✅ Xóa học phần cụ thể khỏi chương trình
    void deleteByChuongTrinhHocIdAndHocPhanId(Long chuongTrinhHocId, Long hocPhanId);

    // ✅ Xóa toàn bộ học phần của chương trình (dùng khi cập nhật lại toàn bộ)
    void deleteByChuongTrinhHocId(Long chuongTrinhHocId);
    
    List<ChuongTrinhHoc_HocPhan> findByChuongTrinhHocIdAndLoaiHocPhanAndToHopIsNull(
        Long chuongTrinhHocId,
        LoaiHocPhan loaiHocPhan
    );
    List<ChuongTrinhHoc_HocPhan> findByChuongTrinhHocIdAndLoaiHocPhan(Long chuongTrinhHocId, LoaiHocPhan loaiHocPhan);
    
    boolean existsByChuongTrinhHocLoaiHocPhanIdAndHocPhanId(Long ctlhpId, Long hocPhanId);
}
