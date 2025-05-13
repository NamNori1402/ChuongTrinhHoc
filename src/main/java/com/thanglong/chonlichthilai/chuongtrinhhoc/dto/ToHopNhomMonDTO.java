package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.thanglong.chonlichthilai.hocphan.HocPhanWithThayTheDTO;

@Data
@Builder
public class ToHopNhomMonDTO {
    private Long id;
    private String tenNhom;
 // Chỉ cần thông tin gọn của ToHopHocPhan
    private Long toHopHocPhanId;
    private String tenToHop;
    private List<HocPhanWithThayTheDTO> hocPhans;
}