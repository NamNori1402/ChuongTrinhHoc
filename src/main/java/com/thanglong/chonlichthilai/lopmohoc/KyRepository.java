package com.thanglong.chonlichthilai.ky;

import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Annotation
@Repository

//Interface

public interface KyRepository extends JpaRepository<Ky, Long> {
    Optional<Ky> findByMaKy(String maKy); // Fetch user by username
}



