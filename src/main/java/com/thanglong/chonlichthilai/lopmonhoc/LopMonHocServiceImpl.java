package com.thanglong.chonlichthilai.lopmonhoc;

//Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Annotation
@Service

//Class
public class LopMonHocServiceImpl implements LopMonHocService {

 @Autowired
 private LopMonHocRepository lopmonhocRepository;
 // Save operation
 @Override
 public LopMonHoc saveLopMonHoc(LopMonHoc lopmonhoc){
     return lopmonhocRepository.save(lopmonhoc);
 }
 // Read operation
 @Override public List<LopMonHoc> fetchLopMonHocList(){
     return (List<LopMonHoc>) lopmonhocRepository.findAll();
 }
 // Read operation
 @Override public LopMonHoc findLopMonHocById(Long id){
     return lopmonhocRepository.findById(id).orElse(null);
 }
 
 @Override public LopMonHoc findLopMonHocByMaHocKy(String maHocKy){
     return lopmonhocRepository.findByMaHocKy(maHocKy).orElse(null);
 }
 // Update operation
 @Override
 public LopMonHoc updateLopMonHoc(LopMonHoc lopmonhoc, Long Id) {
	 LopMonHoc lmh= lopmonhocRepository.findById(Id).get();
     lmh = lopmonhoc;
     return lopmonhocRepository.save(lmh);
 }
 // Delete operation
 @Override
 public void deleteLopMonHocById(Long Id){
	 lopmonhocRepository.deleteById(Id);
 }
}

