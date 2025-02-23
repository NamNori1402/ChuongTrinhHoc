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
	public Date getLastModify() {
		return lastModify;
	}
	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}