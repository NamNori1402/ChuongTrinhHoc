package com.thanglong.chonlichthilai.chuongtrinhhoc.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.thanglong.chonlichthilai.hocphan.HocPhanWithThayTheDTO;
@Data
@Getter
@Setter
public class LoaiHocPhanDTO {
    private Long id;
    private String loaiHocPhan;
    private Integer soTinChiToiThieu;
    private String chuThich; // không bắt buộc
    private List<ToHopHocPhanDTO> toHops;        // ✅ Danh sách tổ hợp thuộc loại học phần
    private List<HocPhanWithThayTheDTO> hocPhansNgoaiToHop; // ✅ Các học phần không thuộc tổ hợp
    private String maBatDauDuocTinh;

}