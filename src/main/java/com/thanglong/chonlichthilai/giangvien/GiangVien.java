package com.thanglong.chonlichthilai.giangvien;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// Annotations 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Class 
public class GiangVien
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String maGiangVien;
    private String password;
    private String hoTenDem;
    private String ten;
    private String donVi;
    private String dienThoai;
    private String email1;
    private String email2;
    private String ghiChu;
    private String hocHam;
    private String hocVi;
    private int giangVien; // giang_vien
    private int coHuu1;
    private int coHuu2;
    private int thinhGiang;
    private int thuKy;
    private int quanTri;
    private int trangThai;
}