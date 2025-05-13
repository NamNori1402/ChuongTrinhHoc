package com.thanglong.chonlichthilai.danhmuc.cathi;

//Importing required classes
import java.util.List;

import com.thanglong.chonlichthilai.danhmuc.cahoc.CaHoc;

//Interface
public interface CaThiService {
	CaThi save(CaThi e);
	List<CaThi> findAll();
	CaThi update(CaThi e, Long id);
	CaThi findById(Long id);
	CaThi findByTenCa(String tenCa);
	void deleteById(Long id);
}