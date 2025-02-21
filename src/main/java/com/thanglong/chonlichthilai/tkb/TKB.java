package com.thanglong.chonlichthilai.tkb;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Use IDENTITY for better consistency
    private Long id;
    
    @Column(unique = true)
    private Integer tt;
    
    private String maKy;
    private String maMon;
    private String tenLop;
    private String trangThai;
    private String maGiangVien;
    
    private String maNguoiNhap; // ✅ Use Integer to allow null values

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