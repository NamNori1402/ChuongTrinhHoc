package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;

import lombok.Data;
import java.util.List;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.LoaiHocPhan;

@Data
public class ChuongTrinhHocRequest {
    private String chuyenNganh;
    private String khoa;
    private List<LoaiHocPhanDTO> loaiHocPhans;
    private List<HocPhanGhepDTO> hocPhans;

    @Data
    public static class HocPhanGhepDTO {
        private Long hocPhanId;
        private LoaiHocPhan loaiHocPhan;
    }
}
