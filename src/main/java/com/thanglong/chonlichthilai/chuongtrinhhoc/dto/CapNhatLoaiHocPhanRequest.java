package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import java.util.List;

import lombok.Data;

@Data
public class CapNhatLoaiHocPhanRequest {
    private Integer soTinChiToiThieu;
    private String chuThich;
    private String maBatDauDuocTinh;
    private List<Long> hocPhanNgoaiToHopIds; // Học phần thuộc loại học phần nhưng KHÔNG nằm trong tổ hợp

    private List<ToHopHocPhanRequest> toHopHocPhans;
}
