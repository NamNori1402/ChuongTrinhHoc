package com.thanglong.chonlichthilai.thongbaonghihoc;


import java.util.List;
import java.util.Optional;
import java.util.Set;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.thongbaonghihoc.ThongBaoNghiHoc;

//Annotation
@Repository

//Interface
public interface ThongBaoNghiHocRepository extends JpaRepository<ThongBaoNghiHoc, Long> {


}

