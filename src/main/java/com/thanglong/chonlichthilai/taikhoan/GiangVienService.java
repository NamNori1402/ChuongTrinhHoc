package com.thanglong.chonlichthilai.taikhoan;

//Importing required classes
import java.util.List;

//Interface
public interface GiangVienService {

 // Save operation
	GiangVien save(GiangVien e);

 // Read TaiKhoan
	List<GiangVien> fetchList();

 // Update operation
	GiangVien update(GiangVien e, Long id);
	
	GiangVien findById(Long id);
	
	GiangVien findByUserName(String userName);
	
 // Delete operation
	void deleteById(Long id);
}