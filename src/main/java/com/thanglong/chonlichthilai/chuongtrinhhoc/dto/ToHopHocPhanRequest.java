// File: ToHopHocPhanRequest.java
package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToHopHocPhanRequest {
    private String tenToHop;
    private Integer soTinChiToiThieu;
    private Integer soLuongMonToiThieu;
    private Boolean batBuocHocTatCa;
    private List<ToHopNhomMonRequest> toHopNhomMons;

    private List<Long> hocPhanIds; // Danh sách ID học phần thuộc tổ hợp
    private Long chuongTrinhHocLoaiHocPhanId;
}
