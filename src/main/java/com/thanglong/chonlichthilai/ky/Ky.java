package com.thanglong.chonlichthilai.ky;

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaKy() {
		return maKy;
	}
	public void setMaKy(String maKy) {
		this.maKy = maKy;
	}
	public String getTenKy() {
		return tenKy;
	}
	public void setTenKy(String tenKy) {
		this.tenKy = tenKy;
	}
	public String getMaNam() {
		return maNam;
	}
	public void setMaNam(String maNam) {
		this.maNam = maNam;
	}
	public Date getBatDauKyHoc() {
		return batDauKyHoc;
	}
	public void setBatDauKyHoc(Date batDauKyHoc) {
		this.batDauKyHoc = batDauKyHoc;
	}
	public Date getKetThucKyHoc() {
		return ketThucKyHoc;
	}
	public void setKetThucKyHoc(Date ketThucKyHoc) {
		this.ketThucKyHoc = ketThucKyHoc;
	}
	public Date getBatDauChonLich() {
		return batDauChonLich;
	}
	public void setBatDauChonLich(Date batDauChonLich) {
		this.batDauChonLich = batDauChonLich;
	}
	public Date getKetThucChonLich() {
		return ketThucChonLich;
	}
	public void setKetThucChonLich(Date ketThucChonLich) {
		this.ketThucChonLich = ketThucChonLich;
	}
	public Date getBatDauLapLich() {
		return batDauLapLich;
	}
	public void setBatDauLapLich(Date batDauLapLich) {
		this.batDauLapLich = batDauLapLich;
	}
	public Date getKetThucLapLich() {
		return ketThucLapLich;
	}
	public void setKetThucLapLich(Date ketThucLapLich) {
		this.ketThucLapLich = ketThucLapLich;
	}
	public Date getLastModify() {
		return lastModify;
	}
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
	
	
}