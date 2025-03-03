package com.thanglong.chonlichthilai.danhmuc.phonghoc;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.giangvien.GiangVien;

//Annotation
@Repository

//Interface
public interface PhongHocRepository extends JpaRepository<PhongHoc, Long> {
    Optional<PhongHoc> findByTenPhong(String maTenPhong);
}

