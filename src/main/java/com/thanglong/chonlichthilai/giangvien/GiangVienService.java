package com.thanglong.chonlichthilai.giangvien;

//Importing required classes
import java.util.List;

//Interface
public interface GiangVienService {

 // Save operation
	GiangVien save(GiangVien e);

 // Read TaiKhoan
	List<GiangVien> findAll();

 // Update operation
	GiangVien update(GiangVien e, Long id);
	
	GiangVien findById(Long id);
	
	GiangVien findByMaGiangVien(String maGiangVien);
	
 // Delete operation
	void deleteById(Long id);
}