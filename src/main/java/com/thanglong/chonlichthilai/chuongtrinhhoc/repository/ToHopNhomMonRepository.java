package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;

public interface ToHopNhomMonRepository extends JpaRepository<ToHopNhomMon, Long> {
    List<ToHopNhomMon> findByToHopHocPhanId(Long toHopId);
}
