package com.thanglong.chonlichthilai.ky;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KyRepository extends JpaRepository<Ky, Long> {
    Optional<Ky> findByMaKy(String maKy); // Lấy theo maKy

    List<Ky> findAllByOrderByMacDinhDescIdDesc(); // Lấy danh sách sắp xếp theo id giảm dần

    @Transactional
    @Modifying
    @Query("UPDATE Ky u set u.macDinh = ?1")
    void updateInBulkByMacDinh(long macDinh);

	Optional<Ky> findByMacDinh(Integer macDinh);
    
}