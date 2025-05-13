package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMonChiTiet;

public interface ToHopNhomMonChiTietRepository extends JpaRepository<ToHopNhomMonChiTiet, Long> {
    List<ToHopNhomMonChiTiet> findByToHopNhomMon_Id(Long toHopNhomMonId);
    void deleteByToHopNhomMon_Id(Long toHopNhomMonId);
    void deleteByToHopNhomMon_IdAndHocPhan_Id(Long toHopNhomMonId, Long hocPhanId);

}