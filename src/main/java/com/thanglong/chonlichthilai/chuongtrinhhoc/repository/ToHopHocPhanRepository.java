//file ToHopHocPhanRepository.java
package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ToHopHocPhanRepository extends JpaRepository<ToHopHocPhan, Long> {
	List<ToHopHocPhan> findByChuongTrinhHocLoaiHocPhan_Id(Long ctLHPId);
	Optional<ToHopHocPhan> findByChuongTrinhHocLoaiHocPhan_IdAndTenToHopIgnoreCase(Long chuongTrinhHocLoaiHocPhanId, String tenToHop);

}