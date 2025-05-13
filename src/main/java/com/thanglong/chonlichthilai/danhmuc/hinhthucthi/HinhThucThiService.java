package com.thanglong.chonlichthilai.danhmuc.hinhthucthi;

//Importing required classes
import java.util.List;

//Interface
public interface HinhThucThiService {
	HinhThucThi save(HinhThucThi e);
	List<HinhThucThi> findAll();
	HinhThucThi update(HinhThucThi e, Long id);
	HinhThucThi findById(Long id);
	HinhThucThi findByTenHinhThucThi(String tenHinhThucThi);
	void deleteById(Long id);
}