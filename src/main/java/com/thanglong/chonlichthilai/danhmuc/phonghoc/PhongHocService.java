package com.thanglong.chonlichthilai.danhmuc.phonghoc;

//Importing required classes
import java.util.List;

//Interface
public interface PhongHocService {
	PhongHoc save(PhongHoc e);
	List<PhongHoc> findAll();
	PhongHoc update(PhongHoc e, Long id);
	PhongHoc findById(Long id);
	PhongHoc findByTenPhong(String tenPhong);
	void deleteById(Long id);
}