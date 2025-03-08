package com.thanglong.chonlichthilai.sinhvien;

import java.util.Collections;
//Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Annotation
@Service

//Class
public class SinhVienServiceImpl implements SinhVienService {

 @Autowired
 private SinhVienRepository repository;
 // Save operation
 @Override
 public SinhVien save(SinhVien e){
     return repository.save(e);
 }
 @Override
 public List<SinhVien> saveAll(List<SinhVien> list) {
     if (list == null || list.isEmpty()) {
         return Collections.emptyList();
     }
     return repository.saveAll(list);
 }
 // Read operation
 @Override public List<SinhVien> findAll(){
     return (List<SinhVien>) repository.findAll();
 }
 @Override
 public SinhVien findFirstByMaSinhVien(String maSinhVien) {
     return repository.findFirstByMaSinhVien(maSinhVien);
 }
 // Read operation
 @Override public SinhVien findById(Long id){
     return repository.findById(id).orElse(null);
 }
 
 @Override public SinhVien findByMaSinhVien(String maSinhVien){
     return repository.findByMaSinhVien(maSinhVien).orElse(null);
 }
 // Update operation
 @Override
 public SinhVien update(SinhVien e, Long Id) {
	 SinhVien k= repository.findById(Id).get();
     k = e;
     return repository.save(k);
 }
 // Delete operation
 @Override
 public void deleteById(Long Id){
	 repository.deleteById(Id);
 }
 @Override
 public void deleteByMaSinhVien(String maSinhVien){
	 repository.deleteByMaSinhVien(maSinhVien);
 }
}

