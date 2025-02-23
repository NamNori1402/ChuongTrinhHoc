package com.thanglong.chonlichthilai.taikhoan;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Annotation
@Repository

//Interface
public interface TaiKhoanRepository extends JpaRepository<GiangVien, Long> {
    Optional<GiangVien> findByUserName(String userName);
}

