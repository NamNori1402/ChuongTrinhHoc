package com.thanglong.chonlichthilai.sinhvien;
//Importing required classes
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.thanglong.chonlichthilai.chuongtrinhhoc.dto.HocPhanDTO;
import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;


public interface SinhVienService {

	 // Save operation
		SinhVien save(SinhVien e);

	 // Read operation
		List<SinhVien> findAll();

	 // Update operation
		SinhVien update(SinhVien e, Long id);
		
		SinhVien findById(Long id);
		
		SinhVien findByMaSinhVien(String e);
		SinhVien findFirstByMaSinhVien(String e);
	 // Delete operation
		void deleteById(Long id);

		void deleteByMaSinhVien(String maSinhVien);

		List<SinhVien> saveAll(List<SinhVien> sinhVienList);
		
		Optional<ChuongTrinhHoc> findChuongTrinhHocCuaSinhVien(String maSinhVien);
	    List<LoaiHocPhanConThieuDTO> getHocPhanConThieu(String maSinhVien);
	}