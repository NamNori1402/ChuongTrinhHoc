package com.thanglong.chonlichthilai.dangkylichthilai;

//Importing required classes
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.entity.CanThiLai;
import com.thanglong.chonlichthilai.entity.ThiLaiResp;

//Interface
public interface DangKyLichThiLaiService {

 // Save operation
	DangKyLichThiLai save(DangKyLichThiLai e);

 // Read TaiKhoan
	List<DangKyLichThiLai> findAll();

 // Update operation
	DangKyLichThiLai update(DangKyLichThiLai e, Long id);
	
	DangKyLichThiLai findById(Long id);
	
	DangKyLichThiLai findByMaSv(String maSv);
	
 // Delete operation
	void deleteById(Long id);


	List<ThiLaiResp> findByMaKy(String maKy);

	List<CanThiLai> findDsCanThiLai(String maKy);
}