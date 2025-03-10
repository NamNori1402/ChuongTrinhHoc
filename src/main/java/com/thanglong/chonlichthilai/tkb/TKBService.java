package com.thanglong.chonlichthilai.tkb;

//Importing required classes
import java.util.List;
import java.util.Set;


//Interface
public interface TKBService {

 // Save operation //Insert
	TKB save(TKB e);

 // Read operation //Select
	List<TKB> findAll();

 // Update operation //Update
	TKB update(TKB e, Long Id);
	
	TKB findById(Long Id);
	
	TKB findByTtTkbTruong(Long tt);
		
 // Delete operation //Delete
	void deleteById(Long Id);

	void deleteAllByMaLopHocPhanIn(Set<String> maLopHocPhanSet);

	void saveAllBatch(List<TKB> tkbBatch);

	List<TKB> findByMaKy(String maKy);
}