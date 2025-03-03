package com.thanglong.chonlichthilai.danhmuc.cahoc;

//Importing required classes
import java.util.List;

//Interface
public interface CaHocService {
	CaHoc save(CaHoc e);
	List<CaHoc> findAll();
	CaHoc update(CaHoc e, Long id);
	CaHoc findById(Long id);
	CaHoc findByTenCa(String tenCa);
	void deleteById(Long id);
}