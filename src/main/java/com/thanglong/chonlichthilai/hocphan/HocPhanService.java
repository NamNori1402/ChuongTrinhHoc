package com.thanglong.chonlichthilai.hocphan;
//Importing required classes
import java.util.List;




//Interface

public interface HocPhanService {

	 // Save operation
	HocPhan save(HocPhan e);

	 // Read operation
		List<HocPhan> findAll();

	 // Update operation
		HocPhan update(HocPhan e, Long id);
		
		HocPhan findById(Long id);
		
		HocPhan findByMaHocPhan(String e);
		
	 // Delete operation
		void deleteById(Long id);

	}