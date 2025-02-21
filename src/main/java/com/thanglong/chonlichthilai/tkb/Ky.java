package com.thanglong.chonlichthilai.tkb;

import java.util.Date;

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
    private String maKy;
    private String tenKy;
    private Date batDauHoc;
    private Date ketThucHoc;
    private Date batDauChonLichThiLai;
    private Date ketThucChonLichThiLai;
    private Date lastModify;
}