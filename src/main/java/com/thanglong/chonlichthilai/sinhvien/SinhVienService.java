package com.thanglong.chonlichthilai.sinhvien;
//Importing required classes
import java.util.List;




//Interface

public interface SinhVienService {

	 // Save operation
    SinhVien saveSinhVien(SinhVien sinhVien);

    // Read operations
    List<SinhVien> fetchSinhVienList();
    SinhVien findSinhVienById(Long id);
    SinhVien findSinhVienByMaSinhVien(String maSinhVien);

    // Update operations
    SinhVien updateSinhVien(SinhVien sinhVien, Long id);
    SinhVien updateSinhVienByMaSinhVien(SinhVien sinhVien, String maSinhVien);

    // Delete operations
    void deleteSinhVienById(Long id);
    void deleteSinhVienByMaSinhVien(String maSinhVien);
	}