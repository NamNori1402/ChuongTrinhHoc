package com.thanglong.chonlichthilai.sinhvien;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

// Class 
public class SinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String maSinhVien;
    private String password;
    private String ten;
    private String hoTenDem;
    private String ngaySinh;
    private String nhapHoc;
    private String lop;
    private String khoa;
    private String lopChuyenNganh;
    private String nganh;
    private String email1;
    private String email2;
    private String dienThoai1;
    private String dienThoai2;
    private String ghiChu;
    private String khoaNhapHoc;
    private int trangThai;
}