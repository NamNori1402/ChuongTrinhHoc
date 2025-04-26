package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import lombok.Data;

import java.util.List;

@Data
public class ToHopNhomMonRequest {
    private String tenNhom;
    private List<Long> hocPhanIds;
}
