package com.thanglong.chonlichthilai.ky;

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
public class Ky {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String maKy; 

    private String tenKy;
    private String maNam;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batDauKyHoc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ketThucKyHoc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batDauChonLich;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ketThucChonLich;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batDauLapLich;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ketThucLapLich;
    private Date lastModify;
    private int macDinh;
}