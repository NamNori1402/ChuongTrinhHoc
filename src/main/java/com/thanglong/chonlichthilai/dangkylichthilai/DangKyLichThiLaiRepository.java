package com.thanglong.chonlichthilai.dangkylichthilai;


import java.util.List;
import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Annotation
@Repository

//Interface
public interface DangKyLichThiLaiRepository extends JpaRepository<DangKyLichThiLai, Long> {
    Optional<DangKyLichThiLai> findByMsv(String msv);
}

