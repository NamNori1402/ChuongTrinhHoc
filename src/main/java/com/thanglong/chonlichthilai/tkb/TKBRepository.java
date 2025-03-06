package com.thanglong.chonlichthilai.tkb;

import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation
@Repository

//Interface
public interface TKBRepository extends JpaRepository<TKB, Long> {
    Optional<TKB> findById(Long id);
    Optional<TKB> findByTtTkbTruong(Long ttTkbTruong);
}

