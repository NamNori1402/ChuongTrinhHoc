package com.thanglong.chonlichthilai.dangky;


import java.util.List;
import java.util.Optional;
import java.util.Set;

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
public interface DangKyRepository extends JpaRepository<DangKy, Long> {
    List<DangKy> findByMsv(String msv);

    @Modifying
    @Transactional
    @Query("DELETE FROM DangKy b WHERE b.msv IN :msvSet")
    void deleteAllByMsv(@Param("msvSet") Set<String> msvSet);

	DangKy findFirstByMsv(String msv);

	List<DangKy> deleteAllByMaKy(String maKy);
}

