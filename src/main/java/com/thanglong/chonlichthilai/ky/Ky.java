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
    private Date batDauHoc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ketThucHoc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date batDauChonLichThiLai;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ketThucChonLichThiLai;
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
	public Date getBatDauHoc() {
		return batDauHoc;
	}
	public void setBatDauHoc(Date batDauHoc) {
		this.batDauHoc = batDauHoc;
	}
	public Date getKetThucHoc() {
		return ketThucHoc;
	}
	public void setKetThucHoc(Date ketThucHoc) {
		this.ketThucHoc = ketThucHoc;
	}
	public Date getBatDauChonLichThiLai() {
		return batDauChonLichThiLai;
	}
	public void setBatDauChonLichThiLai(Date batDauChonLichThiLai) {
		this.batDauChonLichThiLai = batDauChonLichThiLai;
	}
	public Date getKetThucChonLichThiLai() {
		return ketThucChonLichThiLai;
	}
	public void setKetThucChonLichThiLai(Date ketThucChonLichThiLai) {
		this.ketThucChonLichThiLai = ketThucChonLichThiLai;
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
	public String getMaNam() {
		return maNam;
	}
	public void setMaNam(String maNam) {
		this.maNam = maNam;
	}
}