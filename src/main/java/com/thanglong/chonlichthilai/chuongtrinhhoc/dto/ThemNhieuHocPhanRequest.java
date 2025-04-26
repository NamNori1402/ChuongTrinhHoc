package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import java.util.List;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;

import lombok.Data;

@Data
public class ThemNhieuHocPhanRequest {
	private LoaiHocPhan loaiHocPhan;
    private List<Long> hocPhanIds;
}