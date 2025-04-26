package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToHopHocPhanDTO {
    private Long id;
    private String tenToHop;
    private Integer soTinChiToiThieu;
    private Integer soLuongMonToiThieu;
    private Boolean batBuocHocTatCa;
    private List<ToHopNhomMonDTO> toHopNhomMons;

    private List<HocPhanDTO> hocPhans;// Danh sách học phần thuộc tổ hợp
}
