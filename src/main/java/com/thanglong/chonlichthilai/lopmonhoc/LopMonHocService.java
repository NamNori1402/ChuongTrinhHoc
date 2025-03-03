package com.thanglong.chonlichthilai.lopmonhoc;
//Importing required classes
import java.util.List;




//Interface

public interface LopMonHocService {

	 // Save operation
		LopMonHoc saveLopMonHoc(LopMonHoc lopmonhoc);

	 // Read operation
		List<LopMonHoc> fetchLopMonHocList();

	 // Update operation
		LopMonHoc updateLopMonHoc(LopMonHoc lopmonhoc, Long id);
		
		LopMonHoc findLopMonHocById(Long id);
		
		LopMonHoc findLopMonHocByMaHocKy(String maHocKy);
		
	 // Delete operation
		void deleteLopMonHocById(Long id);
	}