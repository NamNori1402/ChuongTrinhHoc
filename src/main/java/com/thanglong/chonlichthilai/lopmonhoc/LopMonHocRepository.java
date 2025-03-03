package com.thanglong.chonlichthilai.lopmonhoc;

import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation
@Repository

//Interface

public interface LopMonHocRepository extends JpaRepository<LopMonHoc, Long> {
    Optional<LopMonHoc> findByMaHocKy(String maHocKy); // Fetch user by username
}



