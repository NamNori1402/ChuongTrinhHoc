package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;
import lombok.Data;
import java.util.List;

@Data
public class ChuongTrinhHocDTO {
    private Long id;
    private String chuyenNganh;
    private String khoa;
    private List<LoaiHocPhanDTO> loaiHocPhans;
    //private List<HocPhanDTO> hocPhans;
}
