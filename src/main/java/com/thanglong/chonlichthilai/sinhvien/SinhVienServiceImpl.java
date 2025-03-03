package com.thanglong.chonlichthilai.sinhvien;

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
 // Read operation
 @Override public List<SinhVien> findAll(){
     return (List<SinhVien>) repository.findAll();
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
}

