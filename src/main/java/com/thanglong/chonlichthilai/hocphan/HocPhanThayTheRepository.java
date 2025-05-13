package com.thanglong.chonlichthilai.hocphan;

import java.util.List;
import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;

import jakarta.transaction.Transactional;


//Annotation
@Repository

public interface HocPhanThayTheRepository extends JpaRepository<HocPhanThayThe, Long> {
    // Tìm theo đối tượng HocPhan (tham chiếu id)
    List<HocPhanThayThe> findByHocPhanThayThe(HocPhan hocPhanThayThe);
    List<HocPhanThayThe> findByHocPhanGoc(HocPhan hocPhanGoc);

    // Xóa theo id của học phần gốc và thay thế (thay vì mã học phần)
    void deleteByHocPhanGoc_IdAndHocPhanThayThe_Id(Long hocPhanGocId, Long hocPhanThayTheId);
	boolean existsByHocPhanGocAndHocPhanThayThe(HocPhan hocPhanGoc, HocPhan hocPhanThayThe);
	    
	@Transactional
    @Modifying
    @Query("DELETE FROM HocPhanThayThe h WHERE h.hocPhanGoc = :hocPhanGoc AND h.hocPhanThayThe = :hocPhanThayThe")
    int deleteByHocPhanGocAndHocPhanThayThe(
        @Param("hocPhanGoc") HocPhan hocPhanGoc,
        @Param("hocPhanThayThe") HocPhan hocPhanThayThe);
}
