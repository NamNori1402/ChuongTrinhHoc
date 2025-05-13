package com.thanglong.chonlichthilai.danhmuc.cahoc;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.giangvien.GiangVien;

//Annotation
@Repository

//Interface
public interface CaHocRepository extends JpaRepository<CaHoc, Long> {
    Optional<CaHoc> findByTenCa(String tenCa);
}

