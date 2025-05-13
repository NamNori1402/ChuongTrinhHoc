package com.thanglong.chonlichthilai.danhmuc.hinhthucthi;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.giangvien.GiangVien;

//Annotation
@Repository

//Interface
public interface HinhThucThiRepository extends JpaRepository<HinhThucThi, Long> {
    Optional<HinhThucThi> findByTenHinhThucThi(String tenHinhThucThi);
}

