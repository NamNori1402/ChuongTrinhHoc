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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTt() {
		return tt;
	}

	public void setTt(Integer tt) {
		this.tt = tt;
	}

	public String getMaKy() {
		return maKy;
	}

	public void setMaKy(String maKy) {
		this.maKy = maKy;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaGiangVien() {
		return maGiangVien;
	}

	public void setMaGiangVien(String maGiangVien) {
		this.maGiangVien = maGiangVien;
	}

	public String getMaNguoiNhap() {
		return maNguoiNhap;
	}

	public void setMaNguoiNhap(String maNguoiNhap) {
		this.maNguoiNhap = maNguoiNhap;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
}