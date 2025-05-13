package com.thanglong.chonlichthilai.dangky;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class DangKy
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msv;
    private String maKy;
    private String maLopHocPhan;
    private String maHocPhan;
    private String nguoiDangKy;
    private Date ngayDangKy;
    private String tinhTrangHocPhi;


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