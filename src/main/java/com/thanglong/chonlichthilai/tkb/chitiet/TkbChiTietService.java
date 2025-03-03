package com.thanglong.chonlichthilai.tkb.chitiet;

//Importing required classes
import java.util.List;


//Interface
public interface TkbChiTietService {

 // Save operation //Insert
	TkbChiTiet save(TkbChiTiet e);

 // Read operation //Select
	List<TkbChiTiet> findAll();

 // Update operation //Update
	TkbChiTiet update(TkbChiTiet e, Long Id);
	
	TkbChiTiet findById(Long Id);
		
 // Delete operation //Delete
	void deleteById(Long Id);
}