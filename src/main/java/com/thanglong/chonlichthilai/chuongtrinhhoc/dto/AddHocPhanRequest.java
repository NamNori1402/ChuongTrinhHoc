package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;

import lombok.Data;

@Data
public class AddHocPhanRequest {
    private Long hocPhanId;
    private LoaiHocPhan loaiHocPhan;
}