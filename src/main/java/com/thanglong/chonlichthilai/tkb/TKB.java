package com.thanglong.chonlichthilai.tkb;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTiet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TKB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(unique = true) 
    private Integer ttTkbTruong; 
    
    private String tenLop; 
        
    
    private String maKy;
    private String maHocPhan;
    
    @Column(unique = true)
    private String maLopHocPhan;  	
    private String maGiangVien;
    private Integer trangThai;
    private String maNguoiNhap;
    private String ghiChu;
    
    private String ngayThi;
    private int caThi;
    private String phongThi;
    private String hinhThucThi;
    private String maNguoiCoiThi;
    private String ghiChuLichThi;
    
    
    
    

    @OneToMany(mappedBy = "tkb", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference  // Prevents infinite recursion
    private List<TkbChiTiet> tkbChiTietList; // ✅ Added orphanRemoval = true

  
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModify;

    @PrePersist
    protected void onCreate() {
        lastModify = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModify = new Date();
    }
}