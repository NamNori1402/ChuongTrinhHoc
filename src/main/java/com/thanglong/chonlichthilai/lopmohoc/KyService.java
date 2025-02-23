package com.thanglong.chonlichthilai.ky;
//Importing required classes
import java.util.List;




//Interface

public interface KyService {

	 // Save operation
		Ky saveKy(Ky ky);

	 // Read operation
		List<Ky> fetchKyList();

	 // Update operation
		Ky updateKy(Ky ky, Long id);
		
		Ky findKyById(Long id);
		
		Ky findKyByMaKy(String maKy);
		
	 // Delete operation
		void deleteKyById(Long id);
	}