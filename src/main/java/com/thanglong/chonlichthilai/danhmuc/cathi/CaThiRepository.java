package com.thanglong.chonlichthilai.danhmuc.cathi;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;



//Annotation
@Repository

public interface CaThiRepository extends JpaRepository<CaThi, Long> {
    Optional<CaThi> findByTenCa(String tenCa);
}

