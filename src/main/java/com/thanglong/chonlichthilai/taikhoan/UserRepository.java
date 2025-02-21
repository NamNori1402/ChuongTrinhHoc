package com.thanglong.chonlichthilai.taikhoan;


import java.util.Optional;

//Java Program to Illustrate DepartmentRepository File


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Annotation
@Repository

//Interface
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName); // Fetch user by username
}

