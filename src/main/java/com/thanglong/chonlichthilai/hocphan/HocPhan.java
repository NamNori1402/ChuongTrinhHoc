package com.thanglong.chonlichthilai.hocphan;

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
    
    @Column(name="so_ca", columnDefinition="int(1) default 2")
    private int soCa;
    
}