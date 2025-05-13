package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChuongTrinhHocRepository extends JpaRepository<ChuongTrinhHoc, Long> {
	boolean existsByChuyenNganhAndKhoa(String chuyenNganh, String khoa);
	//void deleteByChuongTrinhHocId(Long chuongTrinhHocId); //Spring Data JPA sẽ tự động hiểu đây là câu DELETE FROM chuong_trinh_hoc_loai_hoc_phan WHERE chuong_trinh_hoc_id = ?
	void deleteById(Long id);
	Optional<ChuongTrinhHoc> findByChuyenNganhAndKhoa(String chuyenNganh, String khoa);

    // Thay thế các query cũ bằng query mới sử dụng HocPhan
    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.hocPhans hp JOIN hp.hocPhan h WHERE " +
           "LOWER(h.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ChuongTrinhHoc> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.hocPhans hp JOIN hp.hocPhan h WHERE " +
           "LOWER(h.maHocPhan) LIKE LOWER(CONCAT('%', :maHocPhan, '%'))")
    List<ChuongTrinhHoc> findByMaHocPhan(@Param("maHocPhan") String maHocPhan);

    @Query("SELECT DISTINCT c FROM ChuongTrinhHoc c JOIN c.hocPhans hp JOIN hp.hocPhan h WHERE " +
           "LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :tenHocPhan, '%'))")
    List<ChuongTrinhHoc> findByTenHocPhan(@Param("tenHocPhan") String tenHocPhan);
    
 // Tìm kiếm trong tổ hợp học phần
    @Query("""
    SELECT DISTINCT c FROM ChuongTrinhHoc c
    JOIN c.cacLoaiHocPhan lhp
    JOIN lhp.toHops th
    JOIN th.chiTietList ct
    JOIN ct.hocPhan h
    WHERE LOWER(h.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<ChuongTrinhHoc> searchByKeywordInToHop(@Param("keyword") String keyword);

    // Tìm kiếm trong tổ hợp nhóm môn
    @Query("""
    SELECT DISTINCT c FROM ChuongTrinhHoc c
    JOIN c.cacLoaiHocPhan lhp
    JOIN lhp.toHops th
    JOIN th.toHopNhomMons nhom
    JOIN nhom.chiTietMonHoc chiTiet
    JOIN chiTiet.hocPhan h
    WHERE LOWER(h.maHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(h.tenHocPhan) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<ChuongTrinhHoc> searchByKeywordInNhomMon(@Param("keyword") String keyword);

}
