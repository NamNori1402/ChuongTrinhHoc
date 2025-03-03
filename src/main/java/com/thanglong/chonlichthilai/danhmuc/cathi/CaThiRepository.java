package com.thanglong.chonlichthilai.danhmuc.cathi;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



//Annotation
@Repository

//Interface
public interface CaThiRepository extends JpaRepository<CaThi, Long> {
    Optional<CaThi> findByTenCa(String tenCa);
}

