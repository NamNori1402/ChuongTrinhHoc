package com.thanglong.chonlichthilai.hocphan;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class HocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String maHocPhan;       
    private String tenHocPhan;
    private String tienQuyet;
    private String nganhPhuTrach;
    private String lienLac;
    private String maGiangVien;
    private String moRong;
    private Integer soTinChi;   
    @Column(name = "so_ca")
    private Integer soCa;
    @Column(name = "ma_hoc_phan_hien_tai")
    private String maHocPhanHienTai;
    
    @OneToMany(mappedBy = "hocPhanGoc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<HocPhanThayThe> danhSachThayThe;

}