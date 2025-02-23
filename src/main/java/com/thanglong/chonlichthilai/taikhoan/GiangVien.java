package com.thanglong.chonlichthilai.taikhoan;

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
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String hoTenDem;
    private String ten;
    private String donVi;
    private String dienThoai;
    private String email1;
    private String email2;
    private String ghiChu;
    private int giangVien = 1;
    private int thuKy = 0;
    private int quanTri = 0;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHoTenDem() {
		return hoTenDem;
	}
	public void setHoTenDem(String hoTenDem) {
		this.hoTenDem = hoTenDem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public int getGiangVien() {
		return giangVien;
	}
	public void setGiangVien(int giangVien) {
		this.giangVien = giangVien;
	}
	public int getThuKy() {
		return thuKy;
	}
	public void setThuKy(int thuKy) {
		this.thuKy = thuKy;
	}
	public int getQuanTri() {
		return quanTri;
	}
	public void setQuanTri(int quanTri) {
		this.quanTri = quanTri;
	}
}