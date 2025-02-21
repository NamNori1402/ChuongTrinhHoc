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

//Annotations 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Class 
public class LopMonHoc {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private String maHocKy;
 private String maLop;
 private String tenLop;
 private String thu;
 private String gio;
 private String phong;
 private String maGiaoVien;
 private Date lastModify;
}