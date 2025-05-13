package com.thanglong.chonlichthilai.chuongtrinhhoc.service;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopHocPhanRequest;
import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.ToHopNhomMonDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopHocPhan;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ToHopNhomMon;

import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ToHopNhomMonService {
    ToHopNhomMon create(ToHopNhomMon toHopNhomMon);
    List<ToHopNhomMonDTO> getByToHopId(Long toHopId);
    void deleteToHopNhomMon(Long toHopNhomMonId);

    List<String> addHocPhanToNhomMon(Long toHopNhomMonId, List<Long> hocPhanIds); 
    void removeHocPhanFromNhomMon(Long toHopNhomMonId, Long hocPhanId);

}
