package com.thanglong.chonlichthilai.tkb;

//Importing required classes
import java.util.List;
import java.util.Set;

import com.thanglong.chonlichthilai.entity.ThoiKhoaBieu;


//Interface
public interface TKBService {

 // Save operation //Insert
	TKB save(TKB e);

 // Read operation //Select
	List<TKB> findAll();

 // Update operation //Update
	TKB update(TKB e, Long Id);
	
	TKB findById(Long Id);
	
		
 // Delete operation //Delete
	void deleteById(Long Id);

	void deleteAllByMaLopHocPhanIn(Set<String> maLopHocPhanSet);

	void saveAllBatch(List<TKB> tkbBatch);

	List<TKB> findByMaKy(String maKy);

	TKB findByTtTkbTruongAndMaKy(Long ttTkbTruong, String maKy);

	List<ThoiKhoaBieu> findByMaGiangVienMaKy(String maGiangVien, String doiTuong, String maKy);
}