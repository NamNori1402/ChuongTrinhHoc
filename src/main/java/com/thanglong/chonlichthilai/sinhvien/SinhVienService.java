package com.thanglong.chonlichthilai.sinhvien;
//Importing required classes
import java.util.List;




//Interface

public interface SinhVienService {

	 // Save operation
		SinhVien save(SinhVien e);

	 // Read operation
		List<SinhVien> findAll();

	 // Update operation
		SinhVien update(SinhVien e, Long id);
		
		SinhVien findById(Long id);
		
		SinhVien findByMaSinhVien(String e);
		
	 // Delete operation
		void deleteById(Long id);
	}