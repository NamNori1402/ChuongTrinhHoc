package com.thanglong.chonlichthilai.hocphan;

import java.util.Collections;
//Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;


//Annotation
@Service

//Class
public class HocPhanServiceImpl implements HocPhanService {

 @Autowired
 private HocPhanRepository repository;
 // Save operation
 @Override
 public HocPhan save(HocPhan e){
     return repository.save(e);
 }
 // Read operation
 @Override public List<HocPhan> findAll(){
     return (List<HocPhan>) repository.findAll();
 }
 @Override public List<HocPhan> findAllByOrderByMaHocPhanAsc(){
     return (List<HocPhan>) repository.findAllByOrderByMaHocPhanAsc();
 }

 // Read operation
 @Override public HocPhan findById(Long id){
     return repository.findById(id).orElse(null);
 }
 
 @Override public HocPhan findByMaHocPhan(String maHocPhan){
     return repository.findByMaHocPhan(maHocPhan).orElse(null);
 }
 // Update operation
 @Override
 public HocPhan update(HocPhan ky, Long Id) {
	 HocPhan k= repository.findById(Id).get();
     k = ky;
     return repository.save(k);
 }
 // Delete operation
 @Override
 public void deleteById(Long Id){
	 repository.deleteById(Id);
 }
 @Override
 public List<HocPhan> saveAll(List<HocPhan> list) {
     if (list == null || list.isEmpty()) {
         return Collections.emptyList();
     }
     return repository.saveAll(list);
 }
}

