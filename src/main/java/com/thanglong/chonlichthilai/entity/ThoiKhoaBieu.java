package com.thanglong.chonlichthilai.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThoiKhoaBieu {
	String maKy;
	String maHocPhan;
	String maLopHocPhan;
	Integer ttTkbTruong;
	String loaiHocPhan;
	Integer thu;
	String phong;
	Integer batDau;
	Integer ketThuc;

//	 ma_ky, ma_hoc_phan, ma_lop_hoc_phan, tt_tkb_truong, Y.loai_hoc_phan,  " +
//     "Y.thu, Y.phong, Y.bat_dau, Y.ket_thuc
}