package com.thanglong.chonlichthilai.dangnhap;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhienKetNoi {
	String userName;
	String hoTenDem;
	String ten;
	String password;
	String dsQuyen;
	String maKy;
	String namHoc;
	String extent;
	private int trangThai;
}
