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
    private int giangVien = 1;
    private int coHuu1 = 1;
    private int coHuu2 = 0;
    private int thinhGiang = 1;
    private int thuKy = 0;
    private int quanTri = 0;
}