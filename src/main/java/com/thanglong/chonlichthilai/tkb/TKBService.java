package com.thanglong.chonlichthilai.tkb;

//Importing required classes
import java.util.List;


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
}