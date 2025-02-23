package com.thanglong.chonlichthilai.lopmonhoc;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private Date lastModify;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getMaHocKy() {
	return maHocKy;
}
public void setMaHocKy(String maHocKy) {
	this.maHocKy = maHocKy;
}
public String getMaLop() {
	return maLop;
}
public void setMaLop(String maLop) {
	this.maLop = maLop;
}
public String getTenLop() {
	return tenLop;
}
public void setTenLop(String tenLop) {
	this.tenLop = tenLop;
}
public String getThu() {
	return thu;
}
public void setThu(String thu) {
	this.thu = thu;
}
public String getGio() {
	return gio;
}
public void setGio(String gio) {
	this.gio = gio;
}
public String getPhong() {
	return phong;
}
public void setPhong(String phong) {
	this.phong = phong;
}
public String getMaGiaoVien() {
	return maGiaoVien;
}
public void setMaGiaoVien(String maGiaoVien) {
	this.maGiaoVien = maGiaoVien;
}
public Date getLastModify() {
	return lastModify;
}
public void setLastModify(Date lastModify) {
	this.lastModify = lastModify;
}
}