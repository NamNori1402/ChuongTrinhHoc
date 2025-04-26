package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChuongTrinhHocResponse {
    private String message;
    private ChuongTrinhHocDTO data;
}
