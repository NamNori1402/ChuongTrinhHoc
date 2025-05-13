package com.thanglong.chonlichthilai.bangdiem;

//Importing required classes
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//Interface
public interface BangDiemService {

 // Save operation
	BangDiem save(BangDiem e);

 // Read TaiKhoan
	List<BangDiem> findAll();

 // Update operation
	BangDiem update(BangDiem e, Long id);
	
	BangDiem findById(Long id);
	
    BangDiem findFirstByMsv(String msv);  // Ensure it's public
	
	// Delete operation
	void deleteById(Long id);

	String querySQL(String sql);

	void deleteAllByMsv(Set<String>  msv);

	List<BangDiem> findByMsv(String msv);

	List<BangDiem> saveAll(List<BangDiem> list);

}