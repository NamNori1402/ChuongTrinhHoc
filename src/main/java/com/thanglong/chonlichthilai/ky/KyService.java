package com.thanglong.chonlichthilai.ky;
//Importing required classes
import java.util.List;




//Interface

public interface KyService {

	 // Save operation
		Ky save(Ky e);

	 // Read operation
		List<Ky> findAll();

	 // Update operation
		Ky update(Ky e, Long id);
		
		Ky findById(Long id);
		
		Ky findByMaKy(String e);
		Ky findByMacDinh(Integer macDinh);
		
		List<Ky> findAllByOrderByMacDinhDescIdDesc();
		
	 // Delete operation
		void deleteById(Long id);
		void updateInBulkByMacDinh(Long macDinh);

	}