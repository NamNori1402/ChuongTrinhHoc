package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;
@Repository
public interface ToHopNhomMonRepository extends JpaRepository<ToHopNhomMon, Long> {
    List<ToHopNhomMon> findByToHopHocPhanId(Long toHopId);
}
