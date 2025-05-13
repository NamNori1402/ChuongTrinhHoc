package com.thanglong.chonlichthilai.hocphan;

import java.util.List;
import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;


//Annotation
@Repository

//Interface

public interface HocPhanRepository extends JpaRepository<HocPhan, Long> {
    Optional<HocPhan> findByMaHocPhan(String maHocPhan);
    List<HocPhan> findAllByOrderByMaHocPhanAsc();
    List<HocPhan> findByMaHocPhanContainingOrTenHocPhanContaining(String maHocPhan, String tenHocPhan);
    List<HocPhan> findAllByMaHocPhanIn(List<String> maHocPhanList);

 // Tìm theo đầu mã học phần (ví dụ nhập "MA" -> tìm MA*)
    @Query("SELECT h FROM HocPhan h WHERE LOWER(h.maHocPhan) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<HocPhan> findByMaHocPhanPrefix(@Param("prefix") String prefix);
    
    // Tìm mở rộng cả mã và tên
    @Query("SELECT h FROM HocPhan h WHERE " +
           "LOWER(h.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<HocPhan> searchHocPhan(@Param("keyword") String keyword);
}
