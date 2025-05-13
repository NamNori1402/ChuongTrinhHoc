package com.thanglong.chonlichthilai.sinhvien;

import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation
@Repository

//Interface

public interface SinhVienRepository extends JpaRepository<SinhVien, Long> {
    Optional<SinhVien> findByMaSinhVien(String maSinhVien);
    void deleteByMaSinhVien(String maSinhVien);
    SinhVien findFirstByMaSinhVien(String maSinhVien);
}



