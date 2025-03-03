package com.thanglong.chonlichthilai.tkb.chitiet;

import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;


//Annotation
@Repository

//Interface
public interface TkbChiTietRepository extends JpaRepository<TkbChiTiet, Long> {
    Optional<TkbChiTiet> findById(Long id);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM TkbChiTiet t WHERE t.tkb.id = :tkbId")
    void deleteByTkbId(@Param("tkbId") Long tkbId);
}

