package com.thanglong.chonlichthilai.chuongtrinhhoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChuongTrinhHocRepository extends JpaRepository<ChuongTrinhHoc, Long> {
    
    // Tìm kiếm theo mã học phần (không phân biệt hoa thường)
    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.monHocList m WHERE LOWER(m.maHocPhan) LIKE LOWER(CONCAT('%', :maHocPhan, '%'))")
    List<ChuongTrinhHoc> findByMonHocList_MaHocPhanContaining(@Param("maHocPhan") String maHocPhan);

    // Tìm kiếm theo tên môn học (không phân biệt hoa thường)
    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.monHocList m WHERE LOWER(m.tenMonHoc) LIKE LOWER(CONCAT('%', :tenMonHoc, '%'))")
    List<ChuongTrinhHoc> findByMonHocList_TenMonHocContaining(@Param("tenMonHoc") String tenMonHoc);

    // Tìm kiếm kết hợp (không phân biệt hoa thường)
    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.monHocList m WHERE " +
           "LOWER(m.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(m.tenMonHoc) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ChuongTrinhHoc> searchByKeyword(@Param("keyword") String keyword);
}