//file ToHopHocPhanChiTietRepository.java
package com.thanglong.chonlichthilai.chuongtrinhhoc.repository;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhanChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToHopHocPhanChiTietRepository extends JpaRepository<ToHopHocPhanChiTiet, Long> {
    void deleteByToHopHocPhan_Id(Long toHopId); // xóa tổ hợp
    List<ToHopHocPhanChiTiet> findByToHopHocPhanId(Long toHopId); // giữ lại
    void deleteByToHopHocPhan_IdAndHocPhan_Id(Long toHopId, Long hocPhanId);//xóa 1 học phần trong tổ hợp

}
