package com.thanglong.chonlichthilai.entity;

import java.util.Date;





import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanThiLai {
    private Long id;
    private float diemTongKet;
    private String maHocPhan;
    private String msv;
    private Integer soLanThi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date time;
    private Integer soTinChi;
    private String ten;
    private String hoTenDem;
    private String lopChuyenNganh;    
    private String dienThoai1;
}