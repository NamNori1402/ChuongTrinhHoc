package com.thanglong.chonlichthilai.tkb;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation
@Repository

//Interface
public interface TKBRepository extends JpaRepository<TKB, Long> {
    Optional<TKB> findById(Long id);
    Optional<TKB> findByTtTkbTruongAndMaKy(Long ttTkbTruong, String maKy);
    void deleteAllByMaLopHocPhanIn(Set<String> maLopHocPhanSet);
	List<TKB> findByMaKy(String maKy);
}

