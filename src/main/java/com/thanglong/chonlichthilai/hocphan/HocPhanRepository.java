package com.thanglong.chonlichthilai.hocphan;

import java.util.List;
import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;


//Annotation
@Repository

//Interface

public interface HocPhanRepository extends JpaRepository<HocPhan, Long> {
    Optional<HocPhan> findByMaHocPhan(String maHocPhan);
    List<HocPhan> findAllByOrderByMaHocPhanAsc();
}
