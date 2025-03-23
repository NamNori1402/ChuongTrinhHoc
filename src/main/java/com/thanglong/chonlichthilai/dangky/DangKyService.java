package com.thanglong.chonlichthilai.dangky;

//Importing required classes
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//Interface
public interface DangKyService {

 // Save operation
	DangKy save(DangKy e);

 // Read TaiKhoan
	List<DangKy> findAll();

 // Update operation
	DangKy update(DangKy e, Long id);
	
	DangKy findById(Long id);
	
    DangKy findFirstByMsv(String msv);  // Ensure it's public
	
	// Delete operation
	void deleteById(Long id);

	String querySQL(String sql);

	void deleteAllByMsv(Set<String>  msv);
	List<DangKy> deleteAllByMaKy(String maKy);

	List<DangKy> findByMsv(String msv);

	List<DangKy> saveAll(List<DangKy> list);

}