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
public class ThiLaiResp {
    private String maLopHocPhan;
    private Long id;
    private Integer caThi;
    private Date ngayThi;
    private String maGiangVien;
    private String msv;
    private Long chonLichId;
    private String ten;
    private String lopChuyenNganh;
    private String dienThoai1;
}